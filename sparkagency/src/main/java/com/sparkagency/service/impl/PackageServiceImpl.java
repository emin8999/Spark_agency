package com.sparkagency.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparkagency.dto.PackageDto;
import com.sparkagency.entity.PackageEntity;
import com.sparkagency.repository.PackageRepository;
import com.sparkagency.service.PackageService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {
    
  
    private final PackageRepository packageRepository;
    
    @Override
    @Transactional
    public List<PackageDto> getAllPackages() {
        return packageRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public PackageDto createPackage(PackageDto packageDTO) {
        PackageEntity pkg = PackageEntity.builder()
                .nameRu(packageDTO.getNameRu())
                .nameAz(packageDTO.getNameAz())
                .price(packageDTO.getPrice())
                .currency(packageDTO.getCurrency())
                .featuresRu(packageDTO.getFeaturesRu())
                .featuresAz(packageDTO.getFeaturesAz())
                .build();
        
        PackageEntity savedPackage = packageRepository.save(pkg);
        return mapToDTO(savedPackage);
    }
    @Override
    public PackageDto updatePackage(Long id, PackageDto packageDTO) {
        PackageEntity pkg = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        
        pkg.setNameRu(packageDTO.getNameRu());
        pkg.setNameAz(packageDTO.getNameAz());
        pkg.setPrice(packageDTO.getPrice());
        pkg.setCurrency(packageDTO.getCurrency());
        pkg.setFeaturesRu(packageDTO.getFeaturesRu());
        pkg.setFeaturesAz(packageDTO.getFeaturesAz());
        
        PackageEntity savedPackage = packageRepository.save(pkg);
        return mapToDTO(savedPackage);
    }
    @Override
    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }
    
    private PackageDto mapToDTO(PackageEntity pkg) {
        return PackageDto.builder()
                .id(pkg.getId())
                .nameRu(pkg.getNameRu())
                .nameAz(pkg.getNameAz())
                .price(pkg.getPrice())
                .currency(pkg.getCurrency())
                .featuresRu(pkg.getFeaturesRu())
                .featuresAz(pkg.getFeaturesAz())
                .build();
    }
}
