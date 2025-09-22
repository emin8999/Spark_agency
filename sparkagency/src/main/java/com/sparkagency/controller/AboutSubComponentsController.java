package com.sparkagency.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparkagency.dto.AboutStatsDto;
import com.sparkagency.dto.AboutTeamDto;
import com.sparkagency.dto.AboutTimeLineDto;
import com.sparkagency.dto.AboutValuesDto;
import com.sparkagency.service.AboutSubComponentsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AboutSubComponentsController {
    
   
    private final AboutSubComponentsService aboutSubComponentsService;
    
    // Stats endpoints
    @PostMapping("/create/stats")
    public ResponseEntity<AboutStatsDto> createStat(@RequestBody AboutStatsDto statsDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.createStat(statsDTO));
    }
    
    @PutMapping("/update/stats/{id}")
    public ResponseEntity<AboutStatsDto> updateStat(@PathVariable Long id, @RequestBody AboutStatsDto statsDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.updateStat(id, statsDTO));
    }
    
    @DeleteMapping("/delete/stats/{id}")
    public ResponseEntity<Void> deleteStat(@PathVariable("id") Long id) {
        aboutSubComponentsService.deleteStat(id);
        return ResponseEntity.ok().build();
    }
    
    // Values endpoints
    @PostMapping("/create/values")
    public ResponseEntity<AboutValuesDto> createValue(@RequestBody AboutValuesDto valuesDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.createValue(valuesDTO));
    }
    
    @PutMapping("/update/values/{id}")
    public ResponseEntity<AboutValuesDto> updateValue(@PathVariable Long id, @RequestBody AboutValuesDto valuesDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.updateValue(id, valuesDTO));
    }
    
    @DeleteMapping("/delete/values/{id}")
    public ResponseEntity<Void> deleteValue(@PathVariable("id") Long id) {
        aboutSubComponentsService.deleteValue(id);
        return ResponseEntity.ok().build();
    }
    
    // Timeline endpoints
    @PostMapping("/create/timeline")
    public ResponseEntity<AboutTimeLineDto> createTimeline(@RequestBody AboutTimeLineDto timelineDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.createTimeline(timelineDTO));
    }
    
    @PutMapping("/update/timeline/{id}")
    public ResponseEntity<AboutTimeLineDto> updateTimeline(@PathVariable("id") Long id, @RequestBody AboutTimeLineDto timelineDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.updateTimeline(id, timelineDTO));
    }
    
    @DeleteMapping("/delete/timeline/{id}")
    public ResponseEntity<Void> deleteTimeline(@PathVariable("id") Long id) {
        aboutSubComponentsService.deleteTimeline(id);
        return ResponseEntity.ok().build();
    }
    
    // Team endpoints
    @PostMapping("/create/team")
    public ResponseEntity<AboutTeamDto> createTeamMember(@RequestBody AboutTeamDto teamDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.createTeamMember(teamDTO));
    }
    
    @PutMapping("/update/team/{id}")
    public ResponseEntity<AboutTeamDto> updateTeamMember(@PathVariable("id") Long id, @RequestBody AboutTeamDto teamDTO) {
        return ResponseEntity.ok(aboutSubComponentsService.updateTeamMember(id, teamDTO));
    }
    
    @DeleteMapping("/delete/team/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable("id") Long id) {
        aboutSubComponentsService.deleteTeamMember(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/create/team/{id}/avatar/base64")
    public ResponseEntity<AboutTeamDto> uploadTeamAvatar(@PathVariable("id") Long id, @RequestBody Map<String, String> request) {
        try {
            String base64Data = request.get("base64Data");
            return ResponseEntity.ok(aboutSubComponentsService.uploadTeamAvatar(id, base64Data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
