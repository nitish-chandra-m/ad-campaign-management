package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

enum Gender {
    MALE,
    FEMALE,
    ALL
}

enum DeviceType {
    IPAD,
    IPHONE
}

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
    private TargetGroup targetGroup;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public TargetCriteria() {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getGender() {
        return gender.name();
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDeviceType() {
        return deviceType.name();
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = DeviceType.valueOf(deviceType);
    }

    public TargetGroup getTargetGroup() {
        return targetGroup;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
