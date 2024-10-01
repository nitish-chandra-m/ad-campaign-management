package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.Campaign;
import com.github.nitish_chandra_m.ad_campaign_tool.keywords.Keyword;
import com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria.TargetCriteria;
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

    public TargetGroup(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String status,
                       Double maxCpmBid, Campaign campaign) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.status = TargetGroupStatus.valueOf(status);
        this.maxCpmBid = maxCpmBid;
        this.campaign = campaign;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String status) {
        this.status = TargetGroupStatus.valueOf(status);
    }
}

