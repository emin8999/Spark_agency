package com.sparkagency.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.cloudinary.CloudinaryService;
import com.sparkagency.dto.ServiceItemDto;
import com.sparkagency.entity.ServiceEntity;
import com.sparkagency.repository.ServiceRepository;
import com.sparkagency.service.ServiceItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService{
    
    
    private final ServiceRepository serviceRepository;
    

    private final CloudinaryService cloudinaryService;
    
    @Override
    public List<ServiceItemDto> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ServiceItemDto createService(ServiceItemDto serviceDTO) {
        ServiceEntity service = ServiceEntity.builder()
                .icon(serviceDTO.getIcon())
                .iconSrc(serviceDTO.getIconSrc())
                .titleRu(serviceDTO.getTitleRu())
                .titleAz(serviceDTO.getTitleAz())
                .descriptionRu(serviceDTO.getDescriptionRu())
                .descriptionAz(serviceDTO.getDescriptionAz())
                .build();
        
       ServiceEntity savedService = serviceRepository.save(service);
        return mapToDTO(savedService);
    }
    @Override
public ServiceItemDto updateService(Long id, ServiceItemDto serviceDTO) {
    ServiceEntity service = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service not found"));

    if (serviceDTO.getTitleRu() != null) service.setTitleRu(serviceDTO.getTitleRu());
    if (serviceDTO.getTitleAz() != null) service.setTitleAz(serviceDTO.getTitleAz());
    if (serviceDTO.getDescriptionRu() != null) service.setDescriptionRu(serviceDTO.getDescriptionRu());
    if (serviceDTO.getDescriptionAz() != null) service.setDescriptionAz(serviceDTO.getDescriptionAz());
    if (serviceDTO.getIcon() != null) service.setIcon(serviceDTO.getIcon());
    if (serviceDTO.getIconSrc() != null) service.setIconSrc(serviceDTO.getIconSrc());

    ServiceEntity savedService = serviceRepository.save(service);
    return mapToDTO(savedService);
}
    @Override
    public ServiceItemDto uploadServiceIcon(Long id, MultipartFile file) throws IOException {
        String iconUrl = cloudinaryService.uploadImage(file);
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        service.setIconSrc(iconUrl);
        ServiceEntity savedService = serviceRepository.save(service);
        return mapToDTO(savedService);
    }
    @Override
    public ServiceItemDto uploadBase64ServiceIcon(Long id, String base64Data) throws IOException {
        String iconUrl = cloudinaryService.uploadBase64Image(base64Data);
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        service.setIconSrc(iconUrl);
        ServiceEntity savedService = serviceRepository.save(service);
        return mapToDTO(savedService);
    }
    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
    
    private ServiceItemDto mapToDTO(ServiceEntity service) {
        return ServiceItemDto.builder()
                .id(service.getId())
                .icon(service.getIcon())
                .iconSrc(service.getIconSrc())
                .titleRu(service.getTitleRu())
                .titleAz(service.getTitleAz())
                .descriptionRu(service.getDescriptionRu())
                .descriptionAz(service.getDescriptionAz())
                .build();
    }
}

