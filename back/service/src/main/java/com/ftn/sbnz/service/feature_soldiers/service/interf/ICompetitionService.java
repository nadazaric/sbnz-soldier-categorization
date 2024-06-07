package com.ftn.sbnz.service.feature_soldiers.service.interf;

import java.util.List;

import com.ftn.sbnz.model.feature_competitions.dtos.CreateCompetitorDTO;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateSpaCompetitionDTO;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;

public interface ICompetitionService {
    public SpaCompetition createSpaCometition(CreateSpaCompetitionDTO spaCompetitionDTO);
    public SpaCompetition addCompetitorToSpaCompetition(CreateCompetitorDTO competitorDTO);
    public List<SpaCompetition> getSpaCompetitions();
    public SpaCompetition finishCompetition(Long competitionId);
}
