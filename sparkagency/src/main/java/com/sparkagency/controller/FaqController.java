package com.sparkagency.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparkagency.dto.FaqDto;
import com.sparkagency.service.FaqService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/faq")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FaqController {
    
    private final FaqService faqService;
    
    @GetMapping("/get")
    public ResponseEntity<List<FaqDto>> getAllFAQs() {
        return ResponseEntity.ok(faqService.getAllFAQs());
    }
    
    @PostMapping("/create")
    public ResponseEntity<FaqDto> createFAQ(@RequestBody FaqDto faqDTO) {
        return ResponseEntity.ok(faqService.createFAQ(faqDTO));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<FaqDto> updateFAQ(@PathVariable("id") Long id, @RequestBody FaqDto faqDTO) {
        return ResponseEntity.ok(faqService.updateFAQ(id, faqDTO));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFAQ(@PathVariable Long id) {
        faqService.deleteFAQ(id);
        return ResponseEntity.ok().build();
    }
}

