package com.sparkagency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AboutTeamDto {

     private Long id;
    private String name;
    private String roleRu;
    private String roleAz;
    private String avatarSrc;
}
