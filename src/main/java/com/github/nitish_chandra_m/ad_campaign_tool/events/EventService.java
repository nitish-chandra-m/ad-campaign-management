package com.github.nitish_chandra_m.ad_campaign_tool.events;

import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.CampaignMessage;
import com.github.nitish_chandra_m.ad_campaign_tool.utils.JSONUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive(String in) throws Exception {
        var obj = JSONUtil.deserializeJSON(in, CampaignMessage.class);
        System.out.println("Received " + obj.getName() + " " + obj.getType() + " in Event Service");
    }


}
