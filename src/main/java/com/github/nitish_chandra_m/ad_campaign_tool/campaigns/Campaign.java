package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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
    private Status status;

    private List<String> regions;

    @Column(nullable = false)
    private Double budget;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TargetGroup> targetGroups;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

//    For SEARCH campaigns
    public Campaign(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String channel, String status, List<String> regions, Double budget) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.channel = Channel.valueOf(channel);
        this.status = Status.valueOf(status);
        this.regions = regions;
        this.budget = budget;
    }

//    For DISPLAY campaigns
    public Campaign(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String channel, String screen, String placement, String status, List<String> regions, Double budget) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.channel = Channel.valueOf(channel);
        this.screen = Screen.valueOf(screen);
        this.placement = Placement.valueOf(placement);
        this.status = Status.valueOf(status);
        this.regions = regions;
        this.budget = budget;
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

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

}
