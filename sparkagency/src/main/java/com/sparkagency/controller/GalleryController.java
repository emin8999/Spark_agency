package com.sparkagency.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.dto.GalleryDto;
import com.sparkagency.service.GalleryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GalleryController {
    
    
    private final GalleryService galleryService;
    
    @GetMapping("/get")
    public ResponseEntity<List<GalleryDto>> getAllGallery() {
        return ResponseEntity.ok(galleryService.getAllGallery());
    }
    
    @PostMapping("/create")
    public ResponseEntity<GalleryDto> createGalleryItem(@RequestBody GalleryDto galleryDTO) {
        return ResponseEntity.ok(galleryService.createGalleryItem(galleryDTO));
    }
    
    @PostMapping("/upload/image")
    public ResponseEntity<GalleryDto> uploadImage(@RequestParam("file") MultipartFile file, 
                                                  @RequestParam(value = "alt", required = false) String alt) {
        try {
            return ResponseEntity.ok(galleryService.uploadImage(file, alt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/upload/video")
    public ResponseEntity<GalleryDto> uploadVideo(@RequestParam("file") MultipartFile file,
                                                  @RequestParam(value = "alt", required = false) String alt) {
        try {
            return ResponseEntity.ok(galleryService.uploadVideo(file, alt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/upload/image/base64")
    public ResponseEntity<GalleryDto> uploadBase64Image(@RequestBody Map<String, String> request) {
        try {
            String base64Data = request.get("base64Data");
            String alt = request.get("alt");
            return ResponseEntity.ok(galleryService.uploadBase64Image(base64Data, alt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/upload/video/base64")
    public ResponseEntity<GalleryDto> uploadBase64Video(@RequestBody Map<String, String> request) {
        try {
            String base64Data = request.get("base64Data");
            String alt = request.get("alt");
            return ResponseEntity.ok(galleryService.uploadBase64Video(base64Data, alt));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGalleryItem(@PathVariable("id") Long id) {
        galleryService.deleteGalleryItem(id);
        return ResponseEntity.ok().build();
    }
}
