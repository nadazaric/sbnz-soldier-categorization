package com.ftn.sbnz.service.feature_soldiers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.sbnz.model.feature_competitions.dtos.CreateSpaCompetitionDTO;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ICompetitionService;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {

    @Autowired ICompetitionService competitionService;

    @PostMapping
    public ResponseEntity<SpaCompetition> create(@RequestBody CreateSpaCompetitionDTO competitionDTO){
        return new ResponseEntity<>(competitionService.createSpaCometition(competitionDTO), HttpStatus.CREATED);
    }
}
