package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;

import java.time.ZonedDateTime;
import java.util.List;

public record CampaignDto(@NotEmpty String name, @NotEmpty @FutureOrPresent ZonedDateTime startTimestamp, @NotEmpty @Future ZonedDateTime endTimestamp, @NotEmpty String channel, String screen, String placement, CampaignStatus status, List<String> regions, @NotEmpty Double budget) {
}
