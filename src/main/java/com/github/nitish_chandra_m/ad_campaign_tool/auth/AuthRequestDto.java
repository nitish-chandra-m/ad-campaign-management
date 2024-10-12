package com.github.nitish_chandra_m.ad_campaign_tool.auth;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDto(
        @NotNull String email,
        @NotNull String password
) {
}
