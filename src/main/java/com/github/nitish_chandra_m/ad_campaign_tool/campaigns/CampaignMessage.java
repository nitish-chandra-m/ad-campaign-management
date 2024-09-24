package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZonedDateTime;
import java.util.List;

public class CampaignMessage extends CampaignDto {

    private String type;

    public CampaignMessage() {
    }

    public CampaignMessage(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String channel, String screen, String placement, String status, List<String> regions, Double budget, String type) {
        super(name, startTimestamp, endTimestamp, channel, screen, placement, status, regions, budget);
        this.type = type;
    }

    public CampaignMessage(CampaignDto campaignDto, String type) {
        super(campaignDto.getName(), campaignDto.getStartTimestamp(), campaignDto.getEndTimestamp(), campaignDto.getChannel(), campaignDto.getScreen(), campaignDto.getPlacement(), campaignDto.getStatus(), campaignDto.getRegions(), campaignDto.getBudget());
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String serializeToJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
