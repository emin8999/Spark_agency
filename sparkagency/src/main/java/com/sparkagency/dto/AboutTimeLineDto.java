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
public class AboutTimeLineDto {

    private Long id;
    private String year;
    private String textRu;
    private String textAz;
}
