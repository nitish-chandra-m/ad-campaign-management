package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    public CampaignService(CampaignRepository campaignRepository, CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    public List<CampaignDto> getAllCampaigns() {
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
    ) throws Exception {
        var campaign = campaignMapper.toCampaign(campaignDto);
        campaignRepository.save(campaign);
        template.convertAndSend(fanout.getName(), "", String.format("CAMPAIGN_CREATED_%s", campaign.getId().toString()));
        return campaignDto;
    }

    public void deleteCampaignById(String id) throws Exception {
        var c = getCampaignById(id);
        if (c.isEmpty()) {
            throw new Exception("Not Found");
        }
        campaignRepository.deleteById(UUID.fromString(id));

        template.convertAndSend(fanout.getName(), "", String.format("CAMPAIGN_DELETED_%s",id));
    }

    public CampaignDto updateCampaign(String id, CampaignDto campaignDto) throws Exception {
        var c = campaignRepository.findById(UUID.fromString(id));
        if (c.isEmpty()) {
            return null;
        } else {
            Campaign campaignToEdit = c.get();
            campaignToEdit.setName(campaignDto.getName());
            campaignToEdit.setStartTimestamp(campaignDto.getStartTimestamp());
            campaignToEdit.setEndTimestamp(campaignDto.getEndTimestamp());
            campaignToEdit.setChannel(campaignDto.getChannel());
            campaignToEdit.setBudget(campaignDto.getBudget());
            campaignToEdit.setScreen(campaignDto.getScreen());
            campaignToEdit.setPlacement(campaignDto.getPlacement());
            campaignToEdit.setStatus(campaignDto.getStatus());
            campaignToEdit.setRegions(campaignDto.getRegions());
            template.convertAndSend(fanout.getName(), "", String.format("CAMPAIGN_UPDATED_%s",id));
            return campaignDto;
        }
    }
}
