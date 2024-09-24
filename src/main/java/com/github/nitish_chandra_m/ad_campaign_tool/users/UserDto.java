package com.github.nitish_chandra_m.ad_campaign_tool.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        String firstName,
        String lastName,
        @Email @NotNull String email) {
}
