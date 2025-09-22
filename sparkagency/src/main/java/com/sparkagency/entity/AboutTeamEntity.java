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
@Table(name = "about_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutTeamEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "about_id")
    private AboutEntity about;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "role_ru")
    private String roleRu;
    
    @Column(name = "role_az")
    private String roleAz;
    
    @Column(name = "avatar_src", columnDefinition = "TEXT")
    private String avatarSrc;
}
