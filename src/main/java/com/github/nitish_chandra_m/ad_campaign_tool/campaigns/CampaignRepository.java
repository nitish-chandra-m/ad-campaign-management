package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

}
