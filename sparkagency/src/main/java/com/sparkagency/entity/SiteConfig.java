package com.sparkagency.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "siteconfig_entity")
public class SiteConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "brand_name")
    private String brandName;
    
    @Column(name = "logo_src", columnDefinition = "TEXT")
    private String logoSrc;
    
    @Column(name = "hero_title_ru")
    private String heroTitleRu;
    
    @Column(name = "hero_title_az")
    private String heroTitleAz;
    
    @Column(name = "hero_subtitle_ru", columnDefinition = "TEXT")
    private String heroSubtitleRu;
    
    @Column(name = "hero_subtitle_az", columnDefinition = "TEXT")
    private String heroSubtitleAz;
}


