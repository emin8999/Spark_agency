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

import com.sparkagency.dto.PackageDto;
import com.sparkagency.service.PackageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PackageController {
    
   
    private final PackageService packageService;
    
    @GetMapping("/get")
    public ResponseEntity<List<PackageDto>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }
    
    @PostMapping("/create")
    public ResponseEntity<PackageDto> createPackage(@RequestBody PackageDto packageDTO) {
        return ResponseEntity.ok(packageService.createPackage(packageDTO));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<PackageDto> updatePackage(@PathVariable("id") Long id, @RequestBody PackageDto packageDTO) {
        return ResponseEntity.ok(packageService.updatePackage(id, packageDTO));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok().build();
    }
}
