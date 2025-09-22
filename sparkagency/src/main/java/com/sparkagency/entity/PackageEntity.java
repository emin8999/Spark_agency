package com.sparkagency.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "package_entity")
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name_ru")
    private String nameRu;
    
    @Column(name = "name_az")
    private String nameAz;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "currency")
    private String currency;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "package_features_ru", joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "feature")
    private List<String> featuresRu;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "package_features_az", joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "feature")
    private List<String> featuresAz;
    
}
