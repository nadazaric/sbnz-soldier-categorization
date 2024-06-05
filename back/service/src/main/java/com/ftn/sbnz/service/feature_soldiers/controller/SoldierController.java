package com.ftn.sbnz.service.feature_soldiers.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateSoldierDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ISoldierService;
import com.ftn.sbnz.service.feature_soldiers.service.interf.IUnitService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/soldier")
public class SoldierController {

    @Autowired ISoldierService soldierService;
    @Autowired IUnitService unitService;

    @GetMapping
    public ResponseEntity<List<Soldier>> getAll() {
        return new ResponseEntity<>(soldierService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Soldier> create(@RequestBody CreateSoldierDTO soldierDTO){
        Soldier soldier = soldierService.saveSoldier(soldierDTO);
        for (Long id : soldierDTO.getUnits()) unitService.saveUnitForSoldier(soldierDTO.getJmbg(), id);
        return new ResponseEntity<>(soldier, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateSoldierDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(soldierService.getOne(id), HttpStatus.OK);
    }
    
}
