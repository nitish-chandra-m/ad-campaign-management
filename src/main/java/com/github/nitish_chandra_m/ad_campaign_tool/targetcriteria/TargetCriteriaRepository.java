package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TargetCriteriaRepository extends JpaRepository<TargetCriteria, UUID> {
}
