package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateCompetitorDTO;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateSpaCompetitionDTO;
import com.ftn.sbnz.model.feature_competitions.models.Competitor;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import com.ftn.sbnz.service.feature_competitions.repositoty.CompetitorRepository;
import com.ftn.sbnz.service.feature_competitions.repositoty.SpaCompetitionRepostory;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {

    @Autowired SpaCompetitionRepostory spaCompetitionRepostory;
    @Autowired CompetitorRepository competitorRepository;
    @Autowired IKieSessionService kieSessionService;

    @Override
    public SpaCompetition createSpaCometition(CreateSpaCompetitionDTO spaCompetitionDTO) {
        String year = String.valueOf(LocalDate.now().getYear());
        SpaCompetition spaCompetition = new SpaCompetition(spaCompetitionDTO.getName(), year, spaCompetitionDTO.getPositionNumber());
        return spaCompetitionRepostory.save(spaCompetition);
    }

    @Override
    public SpaCompetition addCompetitorToSpaCompetition(CreateCompetitorDTO competitorDTO) {
        KieSession kieSession = kieSessionService.getKieSession();

        Optional<SpaCompetition> spaCompetition = spaCompetitionRepostory.findById(competitorDTO.getCompetitionId());
        if (!spaCompetition.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Competition not exist: " + competitorDTO.getCompetitionId());

        Competitor competitor = new Competitor(
            competitorDTO.getFullName(),
            competitorDTO.getJmbg(),
            competitorDTO.getDeadFamilyMember(),
            competitorDTO.getInjuryType()
        );

        kieSession.insert(competitor);
        kieSession.fireAllRules();
        competitor = competitorRepository.save(competitor);

        spaCompetition.get().addCompetitor(competitor);
        return spaCompetitionRepostory.save(spaCompetition.get());
    }

    @Override
    public List<SpaCompetition> getSpaCompetitions() {
        return spaCompetitionRepostory.findAll();
    }
    
}
