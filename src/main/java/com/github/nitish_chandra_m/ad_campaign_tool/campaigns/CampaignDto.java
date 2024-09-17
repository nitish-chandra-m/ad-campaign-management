package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import com.github.nitish_chandra_m.ad_campaign_tool.utils.EnumValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

public record CampaignDto(
        @NotEmpty String name,
        @NotNull @FutureOrPresent(message = "Time must be in the future.") ZonedDateTime startTimestamp,
        @NotNull @Future ZonedDateTime endTimestamp,
        @NotEmpty @EnumValue(enumClass = Channel.class) String channel,
        @EnumValue(enumClass = Screen.class) String screen,
        @EnumValue(enumClass = Placement.class) String placement,
        @EnumValue(enumClass = Status.class) String status,
        List<String> regions,
        @NotNull Double budget) {
}
