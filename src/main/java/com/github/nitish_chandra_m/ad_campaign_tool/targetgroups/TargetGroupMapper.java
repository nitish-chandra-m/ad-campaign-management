package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.Campaign;
import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TargetGroupMapper {

    private final CampaignRepository campaignRepository;

    public TargetGroupMapper(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public TargetGroup toTargetGroup(TargetGroupDto targetGroupDto) throws Exception {
        String campaignId = targetGroupDto.campaignId();
        var c = campaignRepository.findById(UUID.fromString(campaignId));
        if (c.isEmpty()) {
            throw new Exception("Campaign not found");
        }
        Campaign campaign = c.get();
        return new TargetGroup(targetGroupDto.name(), targetGroupDto.startTimestamp(), targetGroupDto.endTimestamp(),
                TargetGroupStatus.valueOf(targetGroupDto.status()), targetGroupDto.maxCpmBid(), campaign);
    }

    public TargetGroupDto toTargetGroupDto(TargetGroup targetGroup) {
        return new TargetGroupDto(targetGroup.getName(), targetGroup.getStartTimestamp(), targetGroup.getEndTimestamp(),
                targetGroup.getStatus().toString(), targetGroup.getMaxCpmBid(), targetGroup.getId().toString());
    }
}
