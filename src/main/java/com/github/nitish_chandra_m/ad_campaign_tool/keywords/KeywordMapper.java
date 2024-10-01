package com.github.nitish_chandra_m.ad_campaign_tool.keywords;

import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroupRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KeywordMapper {

    private final TargetGroupRepository targetGroupRepository;

    public KeywordMapper(TargetGroupRepository targetGroupRepository) {
        this.targetGroupRepository = targetGroupRepository;
    }

    public Keyword toKeyword(KeywordDto keywordDto) throws Exception {
        var tg = targetGroupRepository.findById(UUID.fromString(keywordDto.targetGroupId()));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        TargetGroup targetGroup = tg.get();

        return new Keyword(keywordDto.text(), keywordDto.type(), keywordDto.match(), targetGroup);
    }

    public KeywordDto toKeywordDto(Keyword keyword) {
        return new KeywordDto(keyword.getText(), keyword.getType(), keyword.getMatch(), keyword.getTargetGroup().toString());
    }
}
