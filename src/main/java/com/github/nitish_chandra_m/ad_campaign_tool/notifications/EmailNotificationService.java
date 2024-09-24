package com.github.nitish_chandra_m.ad_campaign_tool.notifications;

import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.Campaign;
import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.CampaignRepository;
import com.github.nitish_chandra_m.ad_campaign_tool.users.User;
import com.github.nitish_chandra_m.ad_campaign_tool.users.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmailNotificationService {

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;
    private final EmailDispatchService emailDispatchService;

    public EmailNotificationService(UserRepository userRepository, EmailDispatchService emailDispatchService, CampaignRepository campaignRepository) {
        this.userRepository = userRepository;
        this.emailDispatchService = emailDispatchService;
        this.campaignRepository = campaignRepository;
    }

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive(String in) throws Exception {
        String[] msg = in.split("_");
        sendHandler(msg);
    }

    private void sendHandler(String[] message) {

        if (!message[0].equals("CAMPAIGN")) {
            return;
        }
        String eventType = message[1];
        String campaignId = message[2];

        var c = campaignRepository.findById(UUID.fromString(campaignId));
        if (c.isEmpty()) {
            return;
        }
        Campaign campaign = c.get();

        // Fetch all recipient emails from the database
        List<User> recipients = userRepository.findAll();

        // Define email body based on type of event
        String body = String.format("Campaign %s was %s", campaign.getName(), eventType);
        String subject = String.format("[%s] Campaign %s", campaign.getName(), eventType);

        // Spawn a worker thread for each email to be sent
        for (User recipient : recipients) {
            emailDispatchService.sendEmailAsync(recipient.getEmail(), subject, body);
        }
    }
}

