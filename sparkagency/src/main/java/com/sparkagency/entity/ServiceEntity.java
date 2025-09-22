package com.sparkagency.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "service_entity")
public class ServiceEntity {
  
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String icon;
    
    @Column(name = "icon_src", columnDefinition = "TEXT")
    private String iconSrc;
    
    @Column(name = "title_ru")
    private String titleRu;
    
    @Column(name = "title_az")
    private String titleAz;
    
    @Column(name = "description_ru", columnDefinition = "TEXT")
    private String descriptionRu;
    
    @Column(name = "description_az", columnDefinition = "TEXT")
    private String descriptionAz;

}
