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
public class GalleryDto {

    private Long id;
    private String type;
    private String src;
    private String alt;
    private String cloudinaryPublicId;

}
