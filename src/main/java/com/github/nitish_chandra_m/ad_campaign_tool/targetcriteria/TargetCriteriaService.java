package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TargetCriteriaService {

    private final TargetCriteriaRepository targetCriteriaRepository;
    private final TargetGroupRepository targetGroupRepository;

    private final TargetCriteriaMapper targetCriteriaMapper;

    public TargetCriteriaService(TargetCriteriaRepository targetCriteriaRepository, TargetGroupRepository targetGroupRepository, TargetCriteriaMapper targetCriteriaMapper) {
        this.targetCriteriaRepository = targetCriteriaRepository;
        this.targetGroupRepository = targetGroupRepository;
        this.targetCriteriaMapper = targetCriteriaMapper;
    }

    public List<TargetCriteriaDto> getAllTargetCriteria() {
        return targetCriteriaRepository.findAll().stream().map(targetCriteriaMapper::toTargetCriteriaDto).toList();
    }

    public TargetCriteriaDto getTargetCriteriaById(String id) throws Exception {
        var tc = targetCriteriaRepository.findById(UUID.fromString(id));
        if (tc.isEmpty()) {
            throw new Exception("Target Criteria not found");
        }
        TargetCriteria targetCriteria = tc.get();
        return targetCriteriaMapper.toTargetCriteriaDto(targetCriteria);
    }

    public TargetCriteriaDto createTargetCriteria(TargetCriteriaDto targetCriteriaDto) throws Exception {
        TargetCriteria targetCriteria = targetCriteriaMapper.toTargetCriteria(targetCriteriaDto);
        targetCriteriaRepository.save(targetCriteria);
        return targetCriteriaDto;
    }

    public void deleteTargetCriteriaById(String id) throws Exception {
        var tc = targetCriteriaRepository.findById(UUID.fromString(id));
        if (tc.isEmpty()) {
            throw new Exception("Target Criteria not found");
        }
        targetCriteriaRepository.deleteById(tc.get().getId());
    }

    public TargetCriteriaDto updateTargetCriteriaById(String id, TargetCriteriaDto targetCriteriaDto) throws Exception {
        var tc = targetCriteriaRepository.findById(UUID.fromString(id));
        if (tc.isEmpty()) {
            throw new Exception("Target Criteria not found");
        }
        var tg = targetGroupRepository.findById(UUID.fromString(targetCriteriaDto.targetGroupId()));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        TargetGroup targetGroup = tg.get();
        TargetCriteria targetCriteria = tc.get();

        targetCriteria.setMinAge(targetCriteriaDto.minAge());
        targetCriteria.setMaxAge(targetCriteriaDto.maxAge());
        targetCriteria.setGender(targetCriteriaDto.gender());
        targetCriteria.setRegion(targetCriteriaDto.region());
        targetCriteria.setState(targetCriteriaDto.state());
        targetCriteria.setCity(targetCriteriaDto.city());
        targetCriteria.setDeviceType(targetCriteriaDto.deviceType());
        targetCriteria.setTargetGroup(targetGroup);

        return targetCriteriaDto;
    }

}
