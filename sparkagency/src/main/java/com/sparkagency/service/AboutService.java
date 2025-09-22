package com.sparkagency.service;

import com.sparkagency.dto.AboutDto;

public interface AboutService {

    AboutDto getAboutInfo();

    AboutDto updateAboutHero(AboutDto aboutDTO);

    AboutDto uploadHeroImage(String base64Data) throws Exception;

    AboutDto createAboutHero(AboutDto aboutDTO);

}
