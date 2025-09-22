package com.sparkagency.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.dto.SiteDto;
import com.sparkagency.service.SiteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/site")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SiteController {
    
 
    private final SiteService siteService;
    
    @GetMapping("/get")
    public ResponseEntity<SiteDto> getSiteInfo() {
        return ResponseEntity.ok(siteService.getSiteInfo());
    }
    
    @PutMapping("/update")
    public ResponseEntity<SiteDto> updateSiteInfo(@RequestBody SiteDto siteDTO) {
        return ResponseEntity.ok(siteService.updateSiteInfo(siteDTO));
    }

    @PostMapping("/create")
    public ResponseEntity<SiteDto> createSiteInfo(@RequestBody SiteDto siteDTO) {
        return ResponseEntity.ok(siteService.createSiteInfo(siteDTO));
    }
    
    
    @PostMapping("/logo")
    public ResponseEntity<SiteDto> uploadLogo(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(siteService.uploadLogo(file));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/logo/base64")
    public ResponseEntity<SiteDto> uploadBase64Logo(@RequestBody Map<String, String> request) {
        try {
            String base64Data = request.get("base64Data");
            return ResponseEntity.ok(siteService.uploadBase64Logo(base64Data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

