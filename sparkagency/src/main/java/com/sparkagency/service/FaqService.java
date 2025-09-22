package com.sparkagency.service;

import java.util.List;

import com.sparkagency.dto.FaqDto;

public interface FaqService {

    List<FaqDto> getAllFAQs();

    FaqDto createFAQ(FaqDto faqDTO);

    FaqDto updateFAQ(Long id, FaqDto faqDTO);

    void deleteFAQ(Long id);

}
