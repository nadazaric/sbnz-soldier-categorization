package com.ftn.sbnz.service.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.feature_soldiers.dtos.CreateSoldierDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.service.feature_soldiers.repository.SoldierRepository;
import com.ftn.sbnz.service.service.interf.ISoldierService;

@Service
public class SoldierService implements ISoldierService {

    @Autowired SoldierRepository soldierRepository;

    @Override
    public List<Soldier> getAll() {
        return soldierRepository.findAll();
    }

    @Override
    public Soldier saveSoldier(CreateSoldierDTO soldierDTO) {
        
        System.out.println("====================================================================");
        System.err.println(soldierDTO.getFullName());
        Soldier soldier = new Soldier(soldierDTO);
        System.err.println(soldier.getId());
        return soldierRepository.save(soldier);
    }
    
}