package com.github.nitish_chandra_m.ad_campaign_tool.keywords;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "keywords")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private KeywordType type;

    @Column(nullable = false)
    private MatchType match;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "target_group_id")
    private TargetGroup targetGroup;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public Keyword(String text, String type, String match, TargetGroup targetGroup) {
        this.text = text;
        this.type = KeywordType.valueOf(type);
        this.match = MatchType.valueOf(match);
        this.targetGroup = targetGroup;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String type) {
        this.type = KeywordType.valueOf(type);
    }

    public String getMatch() {
        return match.name();
    }

    public void setMatch(String match) {
        this.match = MatchType.valueOf(match);
    }

}
