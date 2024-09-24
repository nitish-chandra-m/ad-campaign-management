package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import org.springframework.stereotype.Service;

@Service
public class CampaignMapper {

    public Campaign toCampaign(CampaignDto campaignDto) {
        if (campaignDto.getChannel().equals("DISPLAY")) {
            return new Campaign(campaignDto.getName(), campaignDto.getStartTimestamp(), campaignDto.getEndTimestamp(), campaignDto.getChannel(), campaignDto.getScreen(), campaignDto.getPlacement(), campaignDto.getStatus(), campaignDto.getRegions(), campaignDto.getBudget());
        } else {
            return new Campaign(campaignDto.getName(), campaignDto.getStartTimestamp(), campaignDto.getEndTimestamp(), campaignDto.getChannel(), campaignDto.getStatus(), campaignDto.getRegions(), campaignDto.getBudget());
        }
    }

    public CampaignDto toCampaignDto(Campaign campaign) {
        return new CampaignDto(campaign.getName(), campaign.getStartTimestamp(), campaign.getEndTimestamp(), campaign.getChannel(), campaign.getScreen(), campaign.getPlacement(), campaign.getStatus(), campaign.getRegions(), campaign.getBudget());
    }
}
