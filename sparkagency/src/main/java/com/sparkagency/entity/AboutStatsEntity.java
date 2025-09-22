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
@Table(name = "about_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutStatsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "about_id")
    private AboutEntity about;
    
    @Column(name = "label_ru")
    private String labelRu;
    
    @Column(name = "label_az")
    private String labelAz;
    
    @Column(name = "value")
    private String value;
}
