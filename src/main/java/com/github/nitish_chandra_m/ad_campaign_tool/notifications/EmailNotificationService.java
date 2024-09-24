package com.github.nitish_chandra_m.ad_campaign_tool.notifications;

import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.CampaignMessage;
import com.github.nitish_chandra_m.ad_campaign_tool.users.User;
import com.github.nitish_chandra_m.ad_campaign_tool.users.UserRepository;
import com.github.nitish_chandra_m.ad_campaign_tool.utils.JSONUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailNotificationService {

    private final UserRepository userRepository;
    private final EmailDispatchService emailDispatchService;

    public EmailNotificationService(UserRepository userRepository, EmailDispatchService emailDispatchService) {
        this.userRepository = userRepository;
        this.emailDispatchService = emailDispatchService;
    }

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive(String in) throws Exception {
        var msg = JSONUtil.deserializeJSON(in, CampaignMessage.class);
        System.out.println("Received " + msg.getName() + " " + msg.getType() + " in Email Notification Service");
        sendHandler(msg);
    }

    private void sendHandler(CampaignMessage campaign) {

        // Fetch all recipient emails from the database
        List<User> recipients = userRepository.findAll();

        // Define email body based on type of event
        String body = String.format("Campaign %s was %s", campaign.getName(), campaign.getType());
        String subject = String.format("[%s] Campaign %s", campaign.getName(), campaign.getType());

        // Spawn a worker thread for each email to be sent
        for (User recipient : recipients) {
            emailDispatchService.sendEmailAsync(recipient.getEmail(), subject, body);
        }
    }
}

@Service
class EmailDispatchService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmailAsync(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
            System.out.println("Email sent to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + to);
            e.printStackTrace();
        }
    }
}
