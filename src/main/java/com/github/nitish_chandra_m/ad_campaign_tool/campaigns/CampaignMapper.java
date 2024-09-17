package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import org.springframework.stereotype.Service;

@Service
public class CampaignMapper {

    public Campaign toCampaign(CampaignDto campaignDto) {
        return new Campaign(campaignDto.name(), campaignDto.startTimestamp(), campaignDto.endTimestamp(), campaignDto.channel(), campaignDto.screen(), campaignDto.placement(), campaignDto.status(), campaignDto.regions(), campaignDto.budget());
    }

    public CampaignDto toCampaignDto(Campaign campaign) {
        return new CampaignDto(campaign.getName(), campaign.getStartTimestamp(), campaign.getEndTimestamp(), campaign.getChannel(), campaign.getScreen(), campaign.getPlacement(), campaign.getStatus(), campaign.getRegions(), campaign.getBudget());
    }
}
