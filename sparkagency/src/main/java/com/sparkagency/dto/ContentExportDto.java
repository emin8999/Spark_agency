package com.sparkagency.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentExportDto {

    private SiteDto site;
    private List<ServiceItemDto> services;
    private List<GalleryDto> gallery;
    private List<PackageDto> packages;
    private List<FaqDto> faq;
    private AboutDto about;
}
