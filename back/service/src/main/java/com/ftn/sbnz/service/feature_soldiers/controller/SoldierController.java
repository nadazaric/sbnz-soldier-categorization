package com.ftn.sbnz.service.feature_soldiers.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.service.service.interf.ISoldierService;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/api/soldier")
public class SoldierController {

    @Autowired ISoldierService soldierService;

    @GetMapping
    public ResponseEntity<List<Soldier>> getAll() {
        return new ResponseEntity<>(soldierService.getAll(), HttpStatus.OK);
    }
    
}
