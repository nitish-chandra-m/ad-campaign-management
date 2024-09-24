package com.github.nitish_chandra_m.ad_campaign_tool.events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
    EventType findByNameContaining(String name);
}
