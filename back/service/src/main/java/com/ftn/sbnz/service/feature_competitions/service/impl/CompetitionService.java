package com.ftn.sbnz.service.feature_competitions.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateCompetitorDTO;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateSpaCompetitionDTO;
import com.ftn.sbnz.model.feature_competitions.models.Competitor;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import com.ftn.sbnz.service.core.values.KieSessionAgendas;
import com.ftn.sbnz.service.feature_competitions.repositoty.CompetitorRepository;
import com.ftn.sbnz.service.feature_competitions.repositoty.SpaCompetitionRepostory;
import com.ftn.sbnz.service.feature_competitions.service.interf.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {

    @Autowired SpaCompetitionRepostory spaCompetitionRepostory;
    @Autowired CompetitorRepository competitorRepository;
    @Autowired IKieSessionService kieSessionService;

    @Override
    public SpaCompetition createSpaCometition(CreateSpaCompetitionDTO spaCompetitionDTO) {
        SpaCompetition spaCompetition = new SpaCompetition(
            spaCompetitionDTO.getName(), 
            LocalDate.now().getYear(), 
            spaCompetitionDTO.getPositionNumber()
        );
        return spaCompetitionRepostory.save(spaCompetition);
    }

    @Override
    public SpaCompetition addCompetitorToSpaCompetition(CreateCompetitorDTO competitorDTO) {
        Optional<SpaCompetition> spaCompetitionOpt = spaCompetitionRepostory.findById(competitorDTO.getCompetitionId());
        if (!spaCompetitionOpt.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Competition not exist: " + competitorDTO.getCompetitionId());
        SpaCompetition spaCompetition = spaCompetitionOpt.get();

        Integer year = LocalDate.now().getYear();
        for (int i = 0; i < 3; i++) {
            System.out.println(year - i);
            List<SpaCompetition> competitionsForYear = spaCompetitionRepostory.findByYear(year - i);
            for (SpaCompetition competition : competitionsForYear) kieSessionService.insertObject(competition);
        }

        Competitor competitor = new Competitor(
            competitorDTO.getFullName(),
            competitorDTO.getJmbg(),
            competitorDTO.getDeadFamilyMember(),
            competitorDTO.getInjuryType()
        );

        kieSessionService.insertObject(competitor);
        kieSessionService.fireRulesForAgenda(KieSessionAgendas.COMPETITORS_AGNDA);

        competitor = competitorRepository.save(competitor);
        spaCompetition.addCompetitor(competitor);
        spaCompetition = spaCompetitionRepostory.save(spaCompetition);
        // spaCompetitionOpt.get().addCompetitor(competitor);

        kieSessionService.fireRulesForAgenda(KieSessionAgendas.SANITAZE);
        
        return spaCompetition;
    }

    @Override
    public List<SpaCompetition> getSpaCompetitions() {
        List<SpaCompetition> competitions = spaCompetitionRepostory.findAll();
        return competitions;
    }

    @Override
    public SpaCompetition finishCompetition(Long competitionId) {
        Optional<SpaCompetition> spaCompetitionForFinish = spaCompetitionRepostory.findById(competitionId);
        if (!spaCompetitionForFinish.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Competition not exist: " + competitionId);
        SpaCompetition spaCompetition = spaCompetitionForFinish.get();

        spaCompetition.setIsDone(true);
        kieSessionService.insertObject(spaCompetition);
        kieSessionService.fireRulesForAgenda(KieSessionAgendas.COMPETITIONS_AGENDA);

        spaCompetition = spaCompetitionRepostory.save(spaCompetition);
        kieSessionService.fireRulesForAgenda(KieSessionAgendas.SANITAZE);

        return spaCompetition;
    }
    
}