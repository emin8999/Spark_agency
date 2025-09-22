package com.sparkagency.entity;

import java.time.LocalDateTime;

import com.sparkagency.enums.MediaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "gallery_entity")
public class GalleryEntity {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MediaType type;
    
    @Column(name = "src", columnDefinition = "TEXT")
    private String src;
    
    @Column(name = "alt_text")
    private String alt;
    
    @Column(name = "cloudinary_public_id")
    private String cloudinaryPublicId;

     public enum MediaType {
        IMAGE, VIDEO
    }
}
