package com.sparkagency.dto;

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
public class AboutStatsDto {

    private Long id;
    private Long aboutId;
    private String labelRu;
    private String labelAz;
    private String value;
}
