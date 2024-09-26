package com.github.nitish_chandra_m.ad_campaign_tool.events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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

    public Event(EventType eventType, UUID campaignId) {
        this.eventType = eventType;
        this.campaignId = campaignId;
    }

}
