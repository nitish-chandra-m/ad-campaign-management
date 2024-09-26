package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import com.github.nitish_chandra_m.ad_campaign_tool.utils.EnumValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.time.ZonedDateTime;

public record TargetGroupDto(
        @NotNull String name,
        @NotNull @FutureOrPresent ZonedDateTime startTimestamp,
        @NotNull @Future ZonedDateTime endTimestamp,
        @NotNull @EnumValue(enumClass = TargetGroupStatus.class) String status,
        @NotNull Double maxCpmBid,
        @NotNull @UUID String campaignId
) {

}
