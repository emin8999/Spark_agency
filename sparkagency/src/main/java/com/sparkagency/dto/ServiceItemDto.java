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
public class ServiceItemDto {

    private Long id;
    private String icon;
    private String iconSrc;
    private String titleRu;
    private String titleAz;
    private String descriptionRu;
    private String descriptionAz;
}
