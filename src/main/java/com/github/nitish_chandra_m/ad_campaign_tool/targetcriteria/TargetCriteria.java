package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

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
@Table(name = "target_criteria")
public class TargetCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer minAge;
    private Integer maxAge;
    private Gender gender;
    private String region;
    private String state;
    private String city;
    private DeviceType deviceType;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "target_group_id")
    private TargetGroup targetGroup;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public TargetCriteria(TargetGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    public TargetCriteria(Integer minAge, Integer maxAge, String gender, String region, String state, String city, String deviceType) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.gender = Gender.valueOf(gender);
        this.region = region;
        this.state = state;
        this.city = city;
        this.deviceType = DeviceType.valueOf(deviceType);
    }

    public String getGender() {
        return gender.name();
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getDeviceType() {
        return deviceType.name();
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = DeviceType.valueOf(deviceType);
    }
}
