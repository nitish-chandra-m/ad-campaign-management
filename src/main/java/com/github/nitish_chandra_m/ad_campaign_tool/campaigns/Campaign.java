package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.nitish_chandra_m.ad_campaign_tool.events.Event;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

enum Channel {
    DISPLAY,
    SEARCH
}

enum Screen {
    TODAY,
    SEARCH,
    PRODUCT,
    SEARCH_RESULT
}

enum Placement {
    TOP,
    BOTTOM
}

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ZonedDateTime startTimestamp;

    @Column(nullable = false)
    private ZonedDateTime endTimestamp;

    @Column(nullable = false)
    private Channel channel;
    private Screen screen;
    private Placement placement;
    private CampaignStatus status;
    private List<String> regions;

    @Column(nullable = false)
    private Double budget;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TargetGroup> targetGroups;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Event> events;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public Campaign() {
    }

//    For SEARCH campaigns
    public Campaign(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String channel, CampaignStatus status, List<String> regions, Double budget) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.channel = Channel.valueOf(channel);
        this.status = status;
        this.regions = regions;
        this.budget = budget;
    }

//    For DISPLAY campaigns
    public Campaign(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String channel, String screen, String placement, CampaignStatus status, List<String> regions, Double budget) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.channel = Channel.valueOf(channel);
        this.screen = Screen.valueOf(screen);
        this.placement = Placement.valueOf(placement);
        this.status = status;
        this.regions = regions;
        this.budget = budget;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(ZonedDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public ZonedDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(ZonedDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getChannel() {
        return channel.name();
    }

    public void setChannel(String channel) {
        this.channel = Channel.valueOf(channel);
    }

    public String getScreen() {
        return screen.name();
    }

    public void setScreen(String screen) {
        this.screen = Screen.valueOf(screen);
    }

    public String getPlacement() {
        return placement.name();
    }

    public void setPlacement(String placement) {
        this.placement = Placement.valueOf(placement);
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<TargetGroup> getTargetGroups() {
        return targetGroups;
    }

    public void setTargetGroups(List<TargetGroup> targetGroups) {
        this.targetGroups = targetGroups;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
