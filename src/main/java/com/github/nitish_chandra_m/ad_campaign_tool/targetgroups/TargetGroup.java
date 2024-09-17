package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.Campaign;
import com.github.nitish_chandra_m.ad_campaign_tool.keywords.Keyword;
import com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria.TargetCriteria;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "target_groups")
public class TargetGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ZonedDateTime startTimestamp;

    @Column(nullable = false)
    private ZonedDateTime endTimestamp;
    private TargetGroupStatus status;

    @Column(nullable = false)
    private Double maxCpmBid;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @JsonBackReference
    private Campaign campaign;

    @OneToMany(mappedBy = "targetGroup")
    @JsonManagedReference
    private List<Keyword> keywords;

    @OneToMany(mappedBy = "targetGroup")
    @JsonManagedReference
    private List<TargetCriteria> targetCriteriaList;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public TargetGroup() {
    }

    public TargetGroup(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, TargetGroupStatus status, Double maxCpmBid, Campaign campaign, List<Keyword> keywords) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.status = status;
        this.maxCpmBid = maxCpmBid;
        this.campaign = campaign;
        this.keywords = keywords;
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

    public TargetGroupStatus getStatus() {
        return status;
    }

    public void setStatus(TargetGroupStatus status) {
        this.status = status;
    }

    public Double getMaxCpmBid() {
        return maxCpmBid;
    }

    public void setMaxCpmBid(Double maxCpmBid) {
        this.maxCpmBid = maxCpmBid;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<TargetCriteria> getTargetCriteriaList() {
        return targetCriteriaList;
    }

    public void setTargetCriteriaList(List<TargetCriteria> targetCriteriaList) {
        this.targetCriteriaList = targetCriteriaList;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
