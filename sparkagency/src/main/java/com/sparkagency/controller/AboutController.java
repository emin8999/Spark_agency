package com.sparkagency.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparkagency.dto.AboutDto;
import com.sparkagency.service.AboutService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AboutController {
    

    private final AboutService aboutService;
    
    @GetMapping("/get")
    public ResponseEntity<AboutDto> getAboutInfo() {
        return ResponseEntity.ok(aboutService.getAboutInfo());
    }

        @PostMapping("/hero")
    public ResponseEntity<AboutDto> createAboutHero(@RequestBody AboutDto aboutDTO) {
        return ResponseEntity.ok(aboutService.createAboutHero(aboutDTO));
    }
    
    @PostMapping("/hero/title")
    public ResponseEntity<AboutDto> createHeroTitle(@RequestBody Map<String, String> request) {
        AboutDto aboutDTO = AboutDto.builder()
                .heroTitleRu(request.get("titleRu"))
                .heroTitleAz(request.get("titleAz"))
                .heroSubtitleRu(request.get("subtitleRu"))
                .heroSubtitleAz(request.get("subtitleAz"))
                .build();
        return ResponseEntity.ok(aboutService.createAboutHero(aboutDTO));
    }
    
    @PutMapping("/hero")
    public ResponseEntity<AboutDto> updateAboutHero(@RequestBody AboutDto aboutDTO) {
        return ResponseEntity.ok(aboutService.updateAboutHero(aboutDTO));
    }
    
    @PostMapping("/hero/image/base64")
    public ResponseEntity<AboutDto> uploadHeroImage(@RequestBody Map<String, String> request) {
        try {
            String base64Data = request.get("base64Data");
            return ResponseEntity.ok(aboutService.uploadHeroImage(base64Data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
