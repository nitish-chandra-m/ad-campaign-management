package com.github.nitish_chandra_m.ad_campaign_tool.keywords;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/list")
    public List<KeywordDto> getAllKeywords() {
        return keywordService.getAllKeywords();
    }

    @GetMapping("/get/{id}")
    public KeywordDto getKeywordById(@PathVariable("id") String id) throws ResponseStatusException {
        try {
            return keywordService.getKeywordById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public KeywordDto createKeyword(
            @Valid @RequestBody KeywordDto keywordDto
    ) throws ResponseStatusException {
        try {
            return keywordService.createKeyword(keywordDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteKeywordById(@PathVariable String id) throws ResponseStatusException {
        try {
            keywordService.deleteKeywordById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public KeywordDto updateKeywordById(@PathVariable String id, @Valid @RequestBody KeywordDto keywordDto) throws ResponseStatusException {
        try {
            return keywordService.updateKeywordById(id, keywordDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
