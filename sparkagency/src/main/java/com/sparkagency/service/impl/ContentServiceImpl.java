package com.sparkagency.service.impl;

import org.springframework.stereotype.Service;

import com.sparkagency.dto.ContentExportDto;
import com.sparkagency.service.AboutService;
import com.sparkagency.service.ContentService;
import com.sparkagency.service.FaqService;
import com.sparkagency.service.GalleryService;
import com.sparkagency.service.PackageService;
import com.sparkagency.service.ServiceItemService;
import com.sparkagency.service.SiteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    
    private SiteService siteService;
    
    private ServiceItemService serviceItemService;
    
    private GalleryService galleryService;
    
    private PackageService packageService;
    
    private FaqService faqService;
    
    private AboutService aboutService;
    
    @Override
    public ContentExportDto exportAllContent() {
        return ContentExportDto.builder()
                .site(siteService.getSiteInfo())
                .services(serviceItemService.getAllServices())
                .gallery(galleryService.getAllGallery())
                .packages(packageService.getAllPackages())
                .faq(faqService.getAllFAQs())
                .about(aboutService.getAboutInfo())
                .build();
    }
}
