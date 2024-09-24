package com.github.nitish_chandra_m.ad_campaign_tool.events;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    private UUID campaignId;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    public Event() {
    }

    public Event(EventType eventType, UUID campaignId) {
        this.eventType = eventType;
        this.campaignId = campaignId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public UUID getCampaignId() {
        return campaignId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
