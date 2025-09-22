package com.sparkagency.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.dto.ServiceItemDto;

public interface ServiceItemService {

    List<ServiceItemDto> getAllServices();

    ServiceItemDto createService(ServiceItemDto serviceDTO);

    ServiceItemDto updateService(Long id, ServiceItemDto serviceDTO);

    ServiceItemDto uploadServiceIcon(Long id, MultipartFile file) throws IOException;

    ServiceItemDto uploadBase64ServiceIcon(Long id, String base64Data) throws IOException;

    void deleteService(Long id);

}
