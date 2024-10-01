package com.github.nitish_chandra_m.ad_campaign_tool.targetgroups;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public TargetGroupDto getTargetGroupById(@PathVariable("id") String id) throws ResponseStatusException {
        try {
            return targetGroupService.getTargetGroupById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public TargetGroupDto createTargetGroup(
            @Valid @RequestBody TargetGroupDto targetGroupDto
    ) throws ResponseStatusException {
        try {
            return targetGroupService.createTargetGroup(targetGroupDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTargetGroupById(@PathVariable String id) throws ResponseStatusException {
        try {
            targetGroupService.deleteTargetGroupById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public TargetGroupDto updateTargetGroupById(@PathVariable String id, @Valid @RequestBody TargetGroupDto targetGroupDto) throws ResponseStatusException {
        try {
            return targetGroupService.updateTargetGroupById(id, targetGroupDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
