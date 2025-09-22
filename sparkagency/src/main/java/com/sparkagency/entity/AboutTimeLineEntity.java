package com.sparkagency.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "about_timeline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutTimeLineEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "about_id")
    private AboutEntity about;
    
    @Column(name = "year")
    private String year;
    
    @Column(name = "text_ru", columnDefinition = "TEXT")
    private String textRu;
    
    @Column(name = "text_az", columnDefinition = "TEXT")
    private String textAz;
}
