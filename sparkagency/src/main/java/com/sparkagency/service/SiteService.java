package com.sparkagency.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.dto.SiteDto;

public interface SiteService {

    SiteDto getSiteInfo();

    SiteDto updateSiteInfo(SiteDto siteDTO);

    SiteDto uploadLogo(MultipartFile file) throws IOException;

    SiteDto uploadBase64Logo(String base64Data) throws IOException;

    SiteDto createSiteInfo(SiteDto siteDTO);

}
