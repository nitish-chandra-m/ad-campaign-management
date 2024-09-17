package com.github.nitish_chandra_m.ad_campaign_tool.campaigns;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/list")
    public List<CampaignDto> getAllCampaigns() {
        return campaignService.findAll();
    }

    @GetMapping("/{id}")
    public CampaignDto getCampaignById(
            @NotEmpty @PathVariable("id") String id
    ) {
        var campaignDto = campaignService.getCampaignById(id);
        if (campaignDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return campaignDto.get();
    }

    @PostMapping("/create")
    public CampaignDto createCampaign(
          @Valid @RequestBody CampaignDto campaignDto
    ) {
        return campaignService.createCampaign(campaignDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCampaign(
           @NotEmpty @PathVariable("id") String id
    ) {
        campaignService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public CampaignDto updateCampaign(
           @NotEmpty @PathVariable("id") String id,
           @Valid @RequestBody CampaignDto campaignDto
    ) {
        var res = campaignService.updateCampaign(id, campaignDto);
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return res;
    }

}
