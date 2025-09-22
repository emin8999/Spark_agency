package com.sparkagency.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.cloudinary.CloudinaryService;
import com.sparkagency.dto.SiteDto;
import com.sparkagency.entity.SiteConfig;
import com.sparkagency.repository.SiteConfigRepository;
import com.sparkagency.service.SiteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteServiceImpl implements SiteService{
    
   
    private final SiteConfigRepository siteRepository;
    
    private final CloudinaryService cloudinaryService;

    @Override
    public SiteDto getSiteInfo() {
        Optional<SiteConfig> site = siteRepository.findById(1L);
        if (site.isPresent()) {
            return mapToDTO(site.get());
        }
        return SiteDto.builder().build();
    }
    @Override
    public SiteDto createSiteInfo(SiteDto siteDTO) {
        SiteConfig site = SiteConfig.builder()
                .brandName(siteDTO.getBrandName())
                .heroTitleRu(siteDTO.getHeroTitleRu())
                .heroTitleAz(siteDTO.getHeroTitleAz())
                .heroSubtitleRu(siteDTO.getHeroSubtitleRu())
                .heroSubtitleAz(siteDTO.getHeroSubtitleAz())
                .logoSrc(siteDTO.getLogoSrc())
                .build();
        
        SiteConfig savedSite = siteRepository.save(site);
        return mapToDTO(savedSite);
    }

    @Override
    public SiteDto updateSiteInfo(SiteDto siteDTO) {
        SiteConfig site = siteRepository.findById(1L).orElse(new SiteConfig());
        site.setBrandName(siteDTO.getBrandName());
        site.setHeroTitleRu(siteDTO.getHeroTitleRu());
        site.setHeroTitleAz(siteDTO.getHeroTitleAz());
        site.setHeroSubtitleRu(siteDTO.getHeroSubtitleRu());
        site.setHeroSubtitleAz(siteDTO.getHeroSubtitleAz());
        site.setLogoSrc(siteDTO.getLogoSrc());
        
        SiteConfig savedSite = siteRepository.save(site);
        return mapToDTO(savedSite);
    }
    @Override
    public SiteDto uploadLogo(MultipartFile file) throws IOException {
        String logoUrl = cloudinaryService.uploadImage(file);
        SiteConfig site = siteRepository.findById(1L).orElse(new SiteConfig());
        site.setLogoSrc(logoUrl);
        SiteConfig savedSite = siteRepository.save(site);
        return mapToDTO(savedSite);
    }
    @Override
    public SiteDto uploadBase64Logo(String base64Data) throws IOException {
        String logoUrl = cloudinaryService.uploadBase64Image(base64Data);
        SiteConfig site = siteRepository.findById(1L).orElse(new SiteConfig());
        site.setLogoSrc(logoUrl);
        SiteConfig savedSite = siteRepository.save(site);
        return mapToDTO(savedSite);
    }
    
    private SiteDto mapToDTO(SiteConfig site) {
        return SiteDto.builder()
                .id(site.getId())
                .brandName(site.getBrandName())
                .logoSrc(site.getLogoSrc())
                .heroTitleRu(site.getHeroTitleRu())
                .heroTitleAz(site.getHeroTitleAz())
                .heroSubtitleRu(site.getHeroSubtitleRu())
                .heroSubtitleAz(site.getHeroSubtitleAz())
                .build();
    }
}
