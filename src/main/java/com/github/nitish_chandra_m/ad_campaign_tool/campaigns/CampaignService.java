package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public CampaignService(CampaignRepository campaignRepository, CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    public List<CampaignDto> findAll() {
        return campaignRepository.findAll()
                .stream()
                .map(campaignMapper::toCampaignDto)
                .collect(Collectors.toList());
    }

    public Optional<CampaignDto> getCampaignById(String id) {
        var campaign = campaignRepository.findById(UUID.fromString(id));
        return campaign.map(campaignMapper::toCampaignDto);
    }

    public CampaignDto createCampaign(
            CampaignDto campaignDto
    ) {
        var campaign = campaignMapper.toCampaign(campaignDto);
        campaignRepository.save(campaign);
        return campaignDto;
    }

    public void deleteById(String id) {
        campaignRepository.deleteById(UUID.fromString(id));
    }

    public CampaignDto updateCampaign(String id, CampaignDto campaignDto) {
        var c = campaignRepository.findById(UUID.fromString(id));
        if (c.isEmpty()) {
            return null;
        } else {
            Campaign campaignToEdit = c.get();
            campaignToEdit.setName(campaignDto.name());
            campaignToEdit.setStartTimestamp(campaignDto.startTimestamp());
            campaignToEdit.setEndTimestamp(campaignDto.endTimestamp());
            campaignToEdit.setChannel(campaignDto.channel());
            campaignToEdit.setBudget(campaignDto.budget());
            campaignToEdit.setScreen(campaignDto.screen());
            campaignToEdit.setPlacement(campaignDto.placement());
            campaignToEdit.setStatus(campaignDto.status());
            campaignToEdit.setRegions(campaignDto.regions());
            return campaignDto;
        }
    }
}
