package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.Campaign;
import com.github.nitish_chandra_m.ad_campaign_tool.campaigns.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TargetGroupService {

    private final TargetGroupRepository targetGroupRepository;
    private final CampaignRepository campaignRepository;
    private final TargetGroupMapper targetGroupMapper;

    public TargetGroupService(TargetGroupRepository targetGroupRepository,
                              CampaignRepository campaignRepository, TargetGroupMapper targetGroupMapper) {
        this.targetGroupRepository = targetGroupRepository;
        this.campaignRepository = campaignRepository;
        this.targetGroupMapper = targetGroupMapper;
    }

    public List<TargetGroupDto> getAllTargetGroups() {
        return targetGroupRepository.findAll().stream().map(targetGroupMapper::toTargetGroupDto).toList();
    }

    public TargetGroupDto getTargetGroupById(String id) throws Exception {
        var tg = targetGroupRepository.findById(UUID.fromString(id));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        TargetGroup targetGroup = tg.get();
        return targetGroupMapper.toTargetGroupDto(targetGroup);
    }

    public TargetGroupDto createTargetGroup(TargetGroupDto targetGroupDto) throws Exception {
        TargetGroup targetGroup = targetGroupMapper.toTargetGroup(targetGroupDto);
        targetGroupRepository.save(targetGroup);
        return targetGroupDto;
    }

    public void deleteTargetGroupById(String id) throws Exception {
        var tg = targetGroupRepository.findById(UUID.fromString(id));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        targetGroupRepository.deleteById(tg.get().getId());
    }

    public TargetGroupDto updateTargetGroupById(String id, TargetGroupDto targetGroupDto) throws Exception {
        var tg = targetGroupRepository.findById(UUID.fromString(id));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        var c = campaignRepository.findById(UUID.fromString(targetGroupDto.campaignId()));
        if (c.isEmpty()) {
            throw new Exception("Campaign not found");
        }
        TargetGroup targetGroup = tg.get();
        Campaign campaign = c.get();

        targetGroup.setName(targetGroupDto.name());
        targetGroup.setStartTimestamp(targetGroupDto.startTimestamp());
        targetGroup.setEndTimestamp(targetGroupDto.endTimestamp());
        targetGroup.setStatus(targetGroupDto.status());
        targetGroup.setMaxCpmBid(targetGroupDto.maxCpmBid());
        targetGroup.setCampaign(campaign);

        return targetGroupDto;
    }

}
