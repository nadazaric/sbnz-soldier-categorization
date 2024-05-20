package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.util.List;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateInjuryDTO;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateSoldierDTO;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateWarDutyDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Injury;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import com.ftn.sbnz.service.feature_soldiers.repository.InjuryRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.SoldierRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.WarDutyRepository;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ISoldierService;

@Service
public class SoldierService implements ISoldierService {

    private final KieSession kieSession;
    
    @Autowired
    public SoldierService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    @Autowired SoldierRepository soldierRepository;
    @Autowired WarDutyRepository warDutyRepository;
    @Autowired InjuryRepository injuryRepository;

    @Override
    public List<Soldier> getAll() {
        return soldierRepository.findAll();
    }

    @Override
    public Soldier saveSoldier(CreateSoldierDTO soldierDTO) {

        Soldier soldier = new Soldier(soldierDTO);
        soldier = soldierRepository.save(soldier);
        this.kieSession.insert(soldier);
        for (CreateWarDutyDTO warDutyDTO : soldierDTO.getWarDuties()) {
            WarDuty warDuty = new WarDuty(warDutyDTO);
            warDuty.setSoldier(soldier);
            warDuty = warDutyRepository.save(warDuty);
            this.kieSession.insert(warDuty);
        }
        for (CreateInjuryDTO injuryDTO : soldierDTO.getInjuries()) {
            Injury injury = new Injury(injuryDTO);
            injury.setSoldier(soldier);
            injury = injuryRepository.save(injury);
            this.kieSession.insert(injury);
        }

        this.kieSession.fireAllRules();
        // this.kieSession.dispose();
        soldierRepository.save(soldier);

        return soldier;
    }
    
}