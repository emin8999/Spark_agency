package com.sparkagency.service;

import java.io.IOException;

import com.sparkagency.dto.AboutStatsDto;
import com.sparkagency.dto.AboutTeamDto;
import com.sparkagency.dto.AboutTimeLineDto;
import com.sparkagency.dto.AboutValuesDto;

public interface AboutSubComponentsService {

    AboutStatsDto updateStat(Long id, AboutStatsDto statsDTO);

    AboutStatsDto createStat(AboutStatsDto statsDTO);

    void deleteStat(Long id);

    AboutValuesDto createValue(AboutValuesDto valuesDTO);

    AboutValuesDto updateValue(Long id, AboutValuesDto valuesDTO);

    void deleteValue(Long id);

    AboutTimeLineDto createTimeline(AboutTimeLineDto timelineDTO);

    AboutTimeLineDto updateTimeline(Long id, AboutTimeLineDto timelineDTO);

    void deleteTimeline(Long id);

    AboutTeamDto createTeamMember(AboutTeamDto teamDTO);

    AboutTeamDto updateTeamMember(Long id, AboutTeamDto teamDTO);

    void deleteTeamMember(Long id);

    AboutTeamDto uploadTeamAvatar(Long id, String base64Data) throws IOException;

}
