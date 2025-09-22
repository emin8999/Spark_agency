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
public class AboutDto {

    private Long id;
    private String heroTitleRu;
    private String heroTitleAz;
    private String heroSubtitleRu;
    private String heroSubtitleAz;
    private String heroImageSrc;
    private List<AboutStatsDto> stats;
    private List<AboutValuesDto> values;
    private List<AboutTimeLineDto> timeline;
    private List<AboutTeamDto> team;
    
}
