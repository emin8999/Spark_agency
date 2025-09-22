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
public class AboutValuesDto {

    private Long id;
    private String valueRu;
    private String valueAz;
    
}
