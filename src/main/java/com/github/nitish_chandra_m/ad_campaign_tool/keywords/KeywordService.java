package com.github.nitish_chandra_m.ad_campaign_tool.keywords;

import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroup;
import com.github.nitish_chandra_m.ad_campaign_tool.targetgroups.TargetGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final TargetGroupRepository targetGroupRepository;

    private final KeywordMapper keywordMapper;

    public KeywordService(KeywordRepository keywordRepository, TargetGroupRepository targetGroupRepository, KeywordMapper keywordMapper) {
        this.keywordRepository = keywordRepository;
        this.targetGroupRepository = targetGroupRepository;
        this.keywordMapper = keywordMapper;
    }

    public List<KeywordDto> getAllKeywords() {
        return keywordRepository.findAll().stream().map(keywordMapper::toKeywordDto).toList();
    }

    public KeywordDto getKeywordById(String id) throws Exception {
        var k = keywordRepository.findById(UUID.fromString(id));
        if (k.isEmpty()) {
            throw new Exception("Keyword not found");
        }
        Keyword keyword = k.get();
        return keywordMapper.toKeywordDto(keyword);
    }

    public KeywordDto createKeyword(KeywordDto keywordDto) throws Exception {
        Keyword keyword = keywordMapper.toKeyword(keywordDto);
        keywordRepository.save(keyword);
        return keywordDto;
    }

    public void deleteKeywordById(String id) throws Exception {
        var k = keywordRepository.findById(UUID.fromString(id));
        if (k.isEmpty()) {
            throw new Exception("Keyword not found");
        }
        keywordRepository.deleteById(k.get().getId());
    }

    public KeywordDto updateKeywordById(String id, KeywordDto keywordDto) throws Exception {
        var k = keywordRepository.findById(UUID.fromString(id));
        if (k.isEmpty()) {
            throw new Exception("Keyword not found");
        }
        var tg = targetGroupRepository.findById(UUID.fromString(keywordDto.targetGroupId()));
        if (tg.isEmpty()) {
            throw new Exception("Target Group not found");
        }
        TargetGroup targetGroup = tg.get();
        Keyword keyword = k.get();

        keyword.setText(keywordDto.text());
        keyword.setType(keywordDto.type());
        keyword.setMatch(keywordDto.match());
        keyword.setTargetGroup(targetGroup);

        return keywordDto;
    }
}
