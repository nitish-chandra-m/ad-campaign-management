package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class TargetCriteriaMapper {

    private final TargetGroupRepository targetGroupRepository;

    public TargetCriteriaMapper(TargetGroupRepository targetGroupRepository) {
        this.targetGroupRepository = targetGroupRepository;
    }

    public TargetCriteria toTargetCriteria(TargetCriteriaDto targetCriteriaDto) throws Exception {
        var tg = targetGroupRepository.findById(UUID.fromString(targetCriteriaDto.targetGroupId()));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        TargetGroup targetGroup = tg.get();

        TargetCriteria targetCriteria = new TargetCriteria(targetGroup);

        setIfValid(targetCriteriaDto.minAge(), targetCriteria::setMinAge, minAge -> minAge > 0);
        setIfValid(targetCriteriaDto.maxAge(), targetCriteria::setMaxAge, maxAge -> maxAge > 0);

        setIfPresent(targetCriteriaDto.gender(), targetCriteria::setGender);
        setIfPresent(targetCriteriaDto.region(), targetCriteria::setRegion);
        setIfPresent(targetCriteriaDto.state(), targetCriteria::setState);
        setIfPresent(targetCriteriaDto.city(), targetCriteria::setCity);
        setIfPresent(targetCriteriaDto.deviceType(), targetCriteria::setDeviceType);

        return targetCriteria;
    }

    public TargetCriteriaDto toTargetCriteriaDto(TargetCriteria targetCriteria) {
        return new TargetCriteriaDto(targetCriteria.getMinAge(), targetCriteria.getMaxAge(), targetCriteria.getGender(),
                targetCriteria.getRegion(), targetCriteria.getState(), targetCriteria.getCity(), targetCriteria.getDeviceType()
        ,targetCriteria.getTargetGroup().toString());
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }

    private <T> void setIfValid(T value, Consumer<T> setter, Predicate<T> validator) {
        if (validator.test(value)) {
            setter.accept(value);
        }
    }

}
