package com.ftn.sbnz.service.feature_competitions.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateCompetitorDTO;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateSpaCompetitionDTO;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;
import com.ftn.sbnz.service.feature_competitions.service.interf.ICompetitionService;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {

    @Autowired ICompetitionService competitionService;

    @PostMapping
    public ResponseEntity<SpaCompetition> create(@RequestBody CreateSpaCompetitionDTO competitionDTO){
        return new ResponseEntity<>(competitionService.createSpaCometition(competitionDTO), HttpStatus.CREATED);
    }

    @PostMapping("/add-competitor")
    public ResponseEntity<SpaCompetition> addCompetitor(@RequestBody CreateCompetitorDTO competitorDTO) {
        return new ResponseEntity<>(competitionService.addCompetitorToSpaCompetition(competitorDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SpaCompetition>> getSpaCompetition() {
        List<SpaCompetition> competitors = competitionService.getSpaCompetitions();
        return new ResponseEntity<>(competitors, HttpStatus.OK);
    }

    @PostMapping("/finish-competition/{competitionId}")
    public ResponseEntity<SpaCompetition> finishCompetition(@PathVariable Long competitionId) {
        return new ResponseEntity<>(competitionService.finishCompetition(competitionId), HttpStatus.OK);
    }
}
