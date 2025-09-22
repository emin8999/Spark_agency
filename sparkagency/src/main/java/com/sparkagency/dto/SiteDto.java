package com.sparkagency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteDto {

    private Long id;
    private String brandName;
    private String logoSrc;
    private String heroTitleRu;
    private String heroTitleAz;
    private String heroSubtitleRu;
    private String heroSubtitleAz;
    
}
