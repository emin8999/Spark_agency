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
public class FaqDto {

    private Long id;
    private String questionRu;
    private String questionAz;
    private String answerRu;
    private String answerAz;
    
}
