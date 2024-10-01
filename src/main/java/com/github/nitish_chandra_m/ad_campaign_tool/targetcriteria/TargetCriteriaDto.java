package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

import com.github.nitish_chandra_m.ad_campaign_tool.utils.EnumValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TargetCriteriaDto(
        @Positive Integer minAge,
        @Positive Integer maxAge,
        @EnumValue(enumClass = Gender.class) String gender,
        String region,
        String state,
        String city,
        @EnumValue(enumClass = DeviceType.class) String deviceType,
        @NotNull String targetGroupId
) {
}
