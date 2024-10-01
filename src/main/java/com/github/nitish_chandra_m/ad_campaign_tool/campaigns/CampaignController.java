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
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/get/{id}")
    public CampaignDto getCampaignById(
            @NotEmpty @PathVariable("id") String id
    ) throws ResponseStatusException {
        var campaignDto = campaignService.getCampaignById(id);
        if (campaignDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return campaignDto.get();
    }

    @PostMapping("/create")
    public CampaignDto createCampaign(
          @Valid @RequestBody CampaignDto campaignDto
    ) throws ResponseStatusException {
        try {
            return campaignService.createCampaign(campaignDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCampaign(
           @NotEmpty @PathVariable("id") String id
    ) throws ResponseStatusException {
        try {
            campaignService.deleteCampaignById(id);
        } catch (Exception e) {
            if (e.getMessage().equals("Not Found")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/update/{id}")
    public CampaignDto updateCampaign(
           @NotEmpty @PathVariable("id") String id,
           @Valid @RequestBody CampaignDto campaignDto
    ) throws ResponseStatusException {
        CampaignDto res = null;
        try {
            res = campaignService.updateCampaign(id, campaignDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

}
