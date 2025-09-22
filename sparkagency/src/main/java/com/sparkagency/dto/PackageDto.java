package com.sparkagency.dto;

import java.math.BigDecimal;
import java.util.List;

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
public class PackageDto {

    private Long id;
    private String nameRu;
    private String nameAz;
    private BigDecimal price;
    private String currency;
    private List<String> featuresRu;
    private List<String> featuresAz;
}
