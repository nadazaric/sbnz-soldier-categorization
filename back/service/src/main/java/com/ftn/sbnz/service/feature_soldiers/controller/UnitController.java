package com.ftn.sbnz.service.feature_soldiers.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.sbnz.model.feature_soldiers.dtos.UnitDTO;
import com.ftn.sbnz.service.feature_soldiers.service.interf.IUnitService;

@RestController
@RequestMapping("/api/unit")
public class UnitController {

    @Autowired private IUnitService unitService;
    
    @GetMapping("/simple-units")
    public ResponseEntity<List<UnitDTO>> getSimpleUnits() {
        return new ResponseEntity<>(unitService.getBasicUnits(), HttpStatus.OK);
    }
}
