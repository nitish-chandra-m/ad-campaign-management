package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TargetGroupRepository extends JpaRepository<TargetGroup, UUID> {
}
