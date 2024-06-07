package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.feature_competitions.dtos.CreateSpaCompetitionDTO;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;
import com.ftn.sbnz.service.feature_competitions.repositoty.SpaCompetitionRepostory;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {

    @Autowired SpaCompetitionRepostory spaCompetitionRepostory;

    @Override
    public SpaCompetition createSpaCometition(CreateSpaCompetitionDTO spaCompetitionDTO) {
        String year = String.valueOf(LocalDate.now().getYear());
        SpaCompetition spaCompetition = new SpaCompetition(spaCompetitionDTO.getName(), year, spaCompetitionDTO.getPositionNumber());
        return spaCompetitionRepostory.save(spaCompetition);
    }
    
}
