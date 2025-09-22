package com.sparkagency.service.impl;

import java.io.IOException;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.sparkagency.cloudinary.CloudinaryService;
import com.sparkagency.dto.AboutStatsDto;
import com.sparkagency.dto.AboutTeamDto;
import com.sparkagency.dto.AboutTimeLineDto;
import com.sparkagency.dto.AboutValuesDto;
import com.sparkagency.entity.AboutEntity;
import com.sparkagency.entity.AboutStatsEntity;
import com.sparkagency.entity.AboutTeamEntity;
import com.sparkagency.entity.AboutTimeLineEntity;
import com.sparkagency.entity.AboutValuesEntity;
import com.sparkagency.repository.AboutRepository;
import com.sparkagency.service.AboutSubComponentsService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AboutSubComponentsServiceImpl implements AboutSubComponentsService{
    
 
    private final AboutRepository aboutRepository;
    
  
    private final CloudinaryService cloudinaryService;
    
   
    @Override
    public AboutStatsDto createStat(AboutStatsDto statsDTO) {
        AboutEntity about = aboutRepository.findById(1L).orElse(new AboutEntity());
        if (about.getId() == null) {
            about = aboutRepository.save(about);
        }
        
        AboutStatsEntity stat = AboutStatsEntity.builder()
                .about(about)
                .labelRu(statsDTO.getLabelRu())
                .labelAz(statsDTO.getLabelAz())
                .value(statsDTO.getValue())
                .build();
        
        about.getStats().add(stat);
        AboutEntity savedAbout = aboutRepository.save(about);

        AboutStatsEntity savedStat = savedAbout.getStats()
        .stream()
        .max(Comparator.comparing(AboutStatsEntity::getId))
        .orElseThrow(() -> new RuntimeException("Stat save failed"));

        
        return mapStatsToDTO(stat);
    }
    
    @Override
    public AboutStatsDto updateStat(Long id, AboutStatsDto statsDTO) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        AboutStatsEntity stat = about.getStats().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stat not found"));
        
        stat.setLabelRu(statsDTO.getLabelRu());
        stat.setLabelAz(statsDTO.getLabelAz());
        stat.setValue(statsDTO.getValue());
        
        aboutRepository.save(about);
        return mapStatsToDTO(stat);
    }
    @Override
    public void deleteStat(Long id) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        about.getStats().removeIf(stat -> stat.getId().equals(id));
        aboutRepository.save(about);
    }
    
   @Override
    public AboutValuesDto createValue(AboutValuesDto valuesDTO) {
        AboutEntity about = aboutRepository.findById(1L).orElse(new AboutEntity());
        if (about.getId() == null) {
            about = aboutRepository.save(about);
        }
        
        AboutValuesEntity value = AboutValuesEntity.builder()
                .about(about)
                .valueRu(valuesDTO.getValueRu())
                .valueAz(valuesDTO.getValueAz())
                .build();
        
        about.getValues().add(value);
        AboutEntity savedAbout = aboutRepository.save(about);
        
        return mapValuesToDTO(value);
    }
     @Override
    public AboutValuesDto updateValue(Long id, AboutValuesDto valuesDTO) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        AboutValuesEntity value = about.getValues().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Value not found"));
        
        value.setValueRu(valuesDTO.getValueRu());
        value.setValueAz(valuesDTO.getValueAz());
        
        aboutRepository.save(about);
        return mapValuesToDTO(value);
    }
     @Override
    public void deleteValue(Long id) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        about.getValues().removeIf(value -> value.getId().equals(id));
        aboutRepository.save(about);
    }
    
    @Override
    public AboutTimeLineDto createTimeline(AboutTimeLineDto timelineDTO) {
        AboutEntity about = aboutRepository.findById(1L).orElse(new AboutEntity());
        if (about.getId() == null) {
            about = aboutRepository.save(about);
        }
        
        AboutTimeLineEntity timeline = AboutTimeLineEntity.builder()
                .about(about)
                .year(timelineDTO.getYear())
                .textRu(timelineDTO.getTextRu())
                .textAz(timelineDTO.getTextAz())
                .build();
        
        about.getTimeline().add(timeline);
        AboutEntity savedAbout = aboutRepository.save(about);
        
        return mapTimelineToDTO(timeline);
    }
     @Override
    public AboutTimeLineDto updateTimeline(Long id, AboutTimeLineDto timelineDTO) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        AboutTimeLineEntity timeline = about.getTimeline().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Timeline not found"));
        
        timeline.setYear(timelineDTO.getYear());
        timeline.setTextRu(timelineDTO.getTextRu());
        timeline.setTextAz(timelineDTO.getTextAz());
        
        aboutRepository.save(about);
        return mapTimelineToDTO(timeline);
    }
     @Override
    public void deleteTimeline(Long id) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        about.getTimeline().removeIf(timeline -> timeline.getId().equals(id));
        aboutRepository.save(about);
    }
     @Override
    public AboutTeamDto createTeamMember(AboutTeamDto teamDTO) {
        AboutEntity about = aboutRepository.findById(1L).orElse(new AboutEntity());
        if (about.getId() == null) {
            about = aboutRepository.save(about);
        }
        
        AboutTeamEntity team = AboutTeamEntity.builder()
                .about(about)
                .name(teamDTO.getName())
                .roleRu(teamDTO.getRoleRu())
                .roleAz(teamDTO.getRoleAz())
                .avatarSrc(teamDTO.getAvatarSrc())
                .build();
        
        about.getTeam().add(team);
        AboutEntity savedAbout = aboutRepository.save(about);
        
        return mapTeamToDTO(team);
    }
     @Override
    public AboutTeamDto updateTeamMember(Long id, AboutTeamDto teamDTO) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        AboutTeamEntity team = about.getTeam().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Team member not found"));
        
        team.setName(teamDTO.getName());
        team.setRoleRu(teamDTO.getRoleRu());
        team.setRoleAz(teamDTO.getRoleAz());
        if (teamDTO.getAvatarSrc() != null) {
            team.setAvatarSrc(teamDTO.getAvatarSrc());
        }
        
        aboutRepository.save(about);
        return mapTeamToDTO(team);
    }
     @Override
    public void deleteTeamMember(Long id) {
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        about.getTeam().removeIf(team -> team.getId().equals(id));
        aboutRepository.save(about);
    }
     @Override
    public AboutTeamDto uploadTeamAvatar(Long id, String base64Data) throws IOException {
        String avatarUrl = cloudinaryService.uploadBase64Image(base64Data);
        
        AboutEntity about = aboutRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("About not found"));
        
        AboutTeamEntity team = about.getTeam().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Team member not found"));
        
        team.setAvatarSrc(avatarUrl);
        aboutRepository.save(about);
        
        return mapTeamToDTO(team);
    }
    
 
    private AboutStatsDto mapStatsToDTO(AboutStatsEntity stats) {
        return AboutStatsDto.builder()
                .id(stats.getId())
                .aboutId(stats.getAbout() != null ? stats.getAbout().getId() : null)
                .labelRu(stats.getLabelRu())
                .labelAz(stats.getLabelAz())
                .value(stats.getValue())
                .build();
    }
    
    private AboutValuesDto mapValuesToDTO(AboutValuesEntity values) {
        return AboutValuesDto.builder()
                .id(values.getId())
                .valueRu(values.getValueRu())
                .valueAz(values.getValueAz())
                .build();
    }
    
    private AboutTimeLineDto mapTimelineToDTO(AboutTimeLineEntity timeline) {
        return AboutTimeLineDto.builder()
                .id(timeline.getId())
                .year(timeline.getYear())
                .textRu(timeline.getTextRu())
                .textAz(timeline.getTextAz())
                .build();
    }
    
    private AboutTeamDto mapTeamToDTO(AboutTeamEntity team) {
        return AboutTeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .roleRu(team.getRoleRu())
                .roleAz(team.getRoleAz())
                .avatarSrc(team.getAvatarSrc())
                .build();
    }
}
