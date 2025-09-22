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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "faq_entity")
public class FaqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "question_ru", columnDefinition = "TEXT")
    private String questionRu;
    
    @Column(name = "question_az", columnDefinition = "TEXT")
    private String questionAz;
    
    @Column(name = "answer_ru", columnDefinition = "TEXT")
    private String answerRu;
    
    @Column(name = "answer_az", columnDefinition = "TEXT")
    private String answerAz;
    
}
