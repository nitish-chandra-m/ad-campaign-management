package com.github.nitish_chandra_m.ad_campaign_tool.keywords;

import com.github.nitish_chandra_m.ad_campaign_tool.utils.EnumValue;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

public record KeywordDto(
        @NotNull String text,
        @NotNull @EnumValue(enumClass = KeywordType.class) String type,
        @NotNull @EnumValue(enumClass = MatchType.class) String match,
        @NotNull @UUID String targetGroupId
) {
}
