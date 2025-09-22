package com.sparkagency.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sparkagency.cloudinary.CloudinaryService;
import com.sparkagency.dto.AboutDto;
import com.sparkagency.dto.AboutStatsDto;
import com.sparkagency.dto.AboutTeamDto;
import com.sparkagency.dto.AboutTimeLineDto;
import com.sparkagency.dto.AboutValuesDto;
import com.sparkagency.entity.AboutEntity;
import com.sparkagency.repository.AboutRepository;
import com.sparkagency.service.AboutService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional  
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {
    
    private final AboutRepository aboutRepository;
    
    private  final CloudinaryService cloudinaryService;
    
    @Override
    public AboutDto getAboutInfo() {
        return aboutRepository.findById(1L)
                .map(this::mapToDTO)
                .orElse(AboutDto.builder().build());
    }

    @Override
    public AboutDto createAboutHero(AboutDto aboutDTO) {
        AboutEntity about = AboutEntity.builder()
                .heroTitleRu(aboutDTO.getHeroTitleRu())
                .heroTitleAz(aboutDTO.getHeroTitleAz())
                .heroSubtitleRu(aboutDTO.getHeroSubtitleRu())
                .heroSubtitleAz(aboutDTO.getHeroSubtitleAz())
                .heroImageSrc(aboutDTO.getHeroImageSrc())
                .build();
        
        AboutEntity savedAbout = aboutRepository.save(about);
        return mapToDTO(savedAbout);
    }
    
    @Override
    public AboutDto updateAboutHero(AboutDto aboutDTO) {
        AboutEntity about = aboutRepository.findById(1L).orElse(new AboutEntity());
        about.setHeroTitleRu(aboutDTO.getHeroTitleRu());
        about.setHeroTitleAz(aboutDTO.getHeroTitleAz());
        about.setHeroSubtitleRu(aboutDTO.getHeroSubtitleRu());
        about.setHeroSubtitleAz(aboutDTO.getHeroSubtitleAz());
        about.setHeroImageSrc(aboutDTO.getHeroImageSrc());
        
        AboutEntity savedAbout = aboutRepository.save(about);
        return mapToDTO(savedAbout);
    }
   @Override 
    public AboutDto uploadHeroImage(String base64Data) throws Exception {
        String imageUrl = cloudinaryService.uploadBase64Image(base64Data);
        AboutEntity about = aboutRepository.findById(1L).orElse(new AboutEntity());
        about.setHeroImageSrc(imageUrl);
        AboutEntity savedAbout = aboutRepository.save(about);
        return mapToDTO(savedAbout);
    }
    
    private AboutDto mapToDTO(AboutEntity about) {
        AboutDto dto = AboutDto.builder()
                .id(about.getId())
                .heroTitleRu(about.getHeroTitleRu())
                .heroTitleAz(about.getHeroTitleAz())
                .heroSubtitleRu(about.getHeroSubtitleRu())
                .heroSubtitleAz(about.getHeroSubtitleAz())
                .heroImageSrc(about.getHeroImageSrc())
                .build();
        
        if (about.getStats() != null) {
            dto.setStats(about.getStats().stream()
                    .map(stat -> AboutStatsDto.builder()
                            .id(stat.getId())
                            .labelRu(stat.getLabelRu())
                            .labelAz(stat.getLabelAz())
                            .value(stat.getValue())
                            .build())
                    .collect(Collectors.toList()));
        }
        
        if (about.getValues() != null) {
            dto.setValues(about.getValues().stream()
                    .map(value -> AboutValuesDto.builder()
                            .id(value.getId())
                            .valueRu(value.getValueRu())
                            .valueAz(value.getValueAz())
                            .build())
                    .collect(Collectors.toList()));
        }
        
        if (about.getTimeline() != null) {
            dto.setTimeline(about.getTimeline().stream()
                    .map(timeline -> AboutTimeLineDto.builder()
                            .id(timeline.getId())
                            .year(timeline.getYear())
                            .textRu(timeline.getTextRu())
                            .textAz(timeline.getTextAz())
                            .build())
                    .collect(Collectors.toList()));
        }
        
        if (about.getTeam() != null) {
            dto.setTeam(about.getTeam().stream()
                    .map(team -> AboutTeamDto.builder()
                            .id(team.getId())
                            .name(team.getName())
                            .roleRu(team.getRoleRu())
                            .roleAz(team.getRoleAz())
                            .avatarSrc(team.getAvatarSrc())
                            .build())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
}
