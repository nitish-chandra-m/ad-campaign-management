package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import com.github.nitish_chandra_m.ad_campaign_tool.utils.EnumValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

public class CampaignDto {
    @NotEmpty
    private String name;

    @NotNull
    @FutureOrPresent(message = "Time must be in the future.")
    private ZonedDateTime startTimestamp;

    @NotNull @Future
    private ZonedDateTime endTimestamp;

    @NotEmpty @EnumValue(enumClass = Channel.class)
    private String channel;

    @EnumValue(enumClass = Screen.class)
    private String screen;

    @EnumValue(enumClass = Placement.class)
    private String placement;

    @EnumValue(enumClass = Status.class)
    private String status;

    private List<String> regions;

    @NotNull
    private Double budget;

    public CampaignDto() {
    }

    public CampaignDto(String name, ZonedDateTime startTimestamp, ZonedDateTime endTimestamp, String channel, String screen, String placement, String status, List<String> regions, Double budget) {
        this.name = name;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.channel = channel;
        this.screen = screen;
        this.placement = placement;
        this.status = status;
        this.regions = regions;
        this.budget = budget;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotNull @FutureOrPresent(message = "Time must be in the future.") ZonedDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(@NotNull @FutureOrPresent(message = "Time must be in the future.") ZonedDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public @NotNull @Future ZonedDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(@NotNull @Future ZonedDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public @NotEmpty @EnumValue(enumClass = Channel.class) String getChannel() {
        return channel;
    }

    public void setChannel(@NotEmpty @EnumValue(enumClass = Channel.class) String channel) {
        this.channel = channel;
    }

    public @EnumValue(enumClass = Screen.class) String getScreen() {
        return screen;
    }

    public void setScreen(@EnumValue(enumClass = Screen.class) String screen) {
        this.screen = screen;
    }

    public @EnumValue(enumClass = Placement.class) String getPlacement() {
        return placement;
    }

    public void setPlacement(@EnumValue(enumClass = Placement.class) String placement) {
        this.placement = placement;
    }

    public @EnumValue(enumClass = Status.class) String getStatus() {
        return status;
    }

    public void setStatus(@EnumValue(enumClass = Status.class) String status) {
        this.status = status;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public @NotNull Double getBudget() {
        return budget;
    }

    public void setBudget(@NotNull Double budget) {
        this.budget = budget;
    }
}
