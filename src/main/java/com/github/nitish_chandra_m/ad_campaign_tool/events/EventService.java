package com.github.nitish_chandra_m.ad_campaign_tool.events;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;

    public EventService(EventRepository eventRepository, EventTypeRepository eventTypeRepository) {
        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive(String in) throws Exception {
        String[] msg = in.split("_");
        addEvent(msg);
    }

    private void addEvent(String[] message) {
        if (!message[0].equals("CAMPAIGN")) {
            return;
        }
        String eventTypeName = message[1];
        String campaignId = message[2];

        EventType eventType = eventTypeRepository.findByNameContaining(eventTypeName);
        Event e = new Event(eventType, UUID.fromString(campaignId));
        eventRepository.save(e);
    }

}
