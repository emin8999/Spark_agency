package com.sparkagency.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.dto.ServiceItemDto;
import com.sparkagency.service.ServiceItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ServiceController {
    


    private final ServiceItemService serviceItemService;
    
    @GetMapping("/get")
    public ResponseEntity<List<ServiceItemDto>> getAllServices() {
        return ResponseEntity.ok(serviceItemService.getAllServices());
    }
    
    @PostMapping("/create")
    public ResponseEntity<ServiceItemDto> createService(@RequestBody ServiceItemDto serviceDTO) {
        return ResponseEntity.ok(serviceItemService.createService(serviceDTO));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceItemDto> updateService(@PathVariable Long id, @RequestBody ServiceItemDto serviceDTO) {
        return ResponseEntity.ok(serviceItemService.updateService(id, serviceDTO));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceItemService.deleteService(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/upload/{id}/icon")
    public ResponseEntity<ServiceItemDto> uploadServiceIcon(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(serviceItemService.uploadServiceIcon(id, file));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/upload/{id}/icon/base64")
    public ResponseEntity<ServiceItemDto> uploadBase64ServiceIcon(@PathVariable("id") Long id, @RequestBody Map<String, String> request) {
        try {
            String base64Data = request.get("base64Data");
            return ResponseEntity.ok(serviceItemService.uploadBase64ServiceIcon(id, base64Data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

  
}

