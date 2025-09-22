package com.sparkagency.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sparkagency.dto.FaqDto;
import com.sparkagency.entity.FaqEntity;
import com.sparkagency.repository.FaqRepository;
import com.sparkagency.service.FaqService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{
    
 
    private final FaqRepository faqRepository;
    
    @Override
    public List<FaqDto> getAllFAQs() {
        return faqRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public FaqDto createFAQ(FaqDto faqDTO) {
        FaqEntity faq = FaqEntity.builder()
                .questionRu(faqDTO.getQuestionRu())
                .questionAz(faqDTO.getQuestionAz())
                .answerRu(faqDTO.getAnswerRu())
                .answerAz(faqDTO.getAnswerAz())
                .build();
        
        FaqEntity savedFAQ = faqRepository.save(faq);
        return mapToDTO(savedFAQ);
    }
    @Override
    public FaqDto updateFAQ(Long id, FaqDto faqDTO) {
        FaqEntity faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));
        
        faq.setQuestionRu(faqDTO.getQuestionRu());
        faq.setQuestionAz(faqDTO.getQuestionAz());
        faq.setAnswerRu(faqDTO.getAnswerRu());
        faq.setAnswerAz(faqDTO.getAnswerAz());
        
        FaqEntity savedFAQ = faqRepository.save(faq);
        return mapToDTO(savedFAQ);
    }
    @Override
    public void deleteFAQ(Long id) {
        faqRepository.deleteById(id);
    }
    
    private FaqDto mapToDTO(FaqEntity faq) {
        return FaqDto.builder()
                .id(faq.getId())
                .questionRu(faq.getQuestionRu())
                .questionAz(faq.getQuestionAz())
                .answerRu(faq.getAnswerRu())
                .answerAz(faq.getAnswerAz())
                .build();
    }
}