package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TargetGroupService {

    private final TargetGroupRepository targetGroupRepository;
    private final TargetGroupMapper targetGroupMapper;

    public TargetGroupService(TargetGroupRepository targetGroupRepository, TargetGroupMapper targetGroupMapper) {
        this.targetGroupRepository = targetGroupRepository;
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


}
