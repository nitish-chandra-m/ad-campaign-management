package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/targetgroups")
public class TargetGroupController {

    private final TargetGroupService targetGroupService;

    public TargetGroupController(TargetGroupService targetGroupService) {
        this.targetGroupService = targetGroupService;
    }

    @GetMapping("/list")
    public List<TargetGroupDto> getAllTargetGroups() {
        return targetGroupService.getAllTargetGroups();
    }

    @GetMapping("/get/{id}")
    public TargetGroupDto getTargetGroupById(@PathVariable("id") String id) {
        try {
            return targetGroupService.getTargetGroupById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
