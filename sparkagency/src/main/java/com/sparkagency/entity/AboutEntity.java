package com.sparkagency.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "about_entity")
public class AboutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "hero_title_ru")
    private String heroTitleRu;
    
    @Column(name = "hero_title_az")
    private String heroTitleAz;
    
    @Column(name = "hero_subtitle_ru", columnDefinition = "TEXT")
    private String heroSubtitleRu;
    
    @Column(name = "hero_subtitle_az", columnDefinition = "TEXT")
    private String heroSubtitleAz;
    
    @Column(name = "hero_image_src", columnDefinition = "TEXT")
    private String heroImageSrc;
    
    @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AboutStatsEntity> stats;
    
    @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AboutValuesEntity> values;
    
    @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AboutTimeLineEntity> timeline;
    
    @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AboutTeamEntity> team;

}
