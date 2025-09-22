package com.sparkagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparkagency.dto.ContentExportDto;
import com.sparkagency.service.ContentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContentController {
    
    
    private final ContentService contentService;
    
    @GetMapping("/export")
    public ResponseEntity<ContentExportDto> exportAllContent() {
        return ResponseEntity.ok(contentService.exportAllContent());
    }
}
