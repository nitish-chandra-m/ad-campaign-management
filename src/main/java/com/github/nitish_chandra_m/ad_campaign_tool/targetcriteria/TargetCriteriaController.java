package com.github.nitish_chandra_m.ad_campaign_tool.targetcriteria;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/targetcriteria")
public class TargetCriteriaController {

    private final TargetCriteriaService targetCriteriaService;

    public TargetCriteriaController(TargetCriteriaService targetCriteriaService) {
        this.targetCriteriaService = targetCriteriaService;
    }

    @GetMapping("/list")
    public List<TargetCriteriaDto> getAllTargetCriteria() {
        return targetCriteriaService.getAllTargetCriteria();
    }

    @GetMapping("/get/{id}")
    public TargetCriteriaDto getTargetGroupById(@PathVariable("id") String id) throws ResponseStatusException {
        try {
            return targetCriteriaService.getTargetCriteriaById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public TargetCriteriaDto createTargetGroup(
            @Valid @RequestBody TargetCriteriaDto targetGroupDto
    ) throws ResponseStatusException {
        try {
            return targetCriteriaService.createTargetCriteria(targetGroupDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTargetGroupById(@PathVariable String id) throws ResponseStatusException {
        try {
            targetCriteriaService.deleteTargetCriteriaById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public TargetCriteriaDto updateTargetGroupById(@PathVariable String id, @Valid @RequestBody TargetCriteriaDto targetCriteriaDto) throws ResponseStatusException {
        try {
            return targetCriteriaService.updateTargetCriteriaById(id, targetCriteriaDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
