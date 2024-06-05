package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateInjuryDTO;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateSoldierDTO;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateWarDutyDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Injury;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import com.ftn.sbnz.service.feature_soldiers.repository.InjuryRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.SoldierRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.WarDutyRepository;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ISoldierService;

@Service
public class SoldierService implements ISoldierService {

    @Autowired SoldierRepository soldierRepository;
    @Autowired WarDutyRepository warDutyRepository;
    @Autowired InjuryRepository injuryRepository;
    @Autowired IKieSessionService kieSessionService;

    @Override
    public List<Soldier> getAll() {
        return soldierRepository.findAll();
    }

    @Override
    public Soldier saveSoldier(CreateSoldierDTO soldierDTO) {
        KieSession kieSession = kieSessionService.getKieSession();

        Soldier soldier = new Soldier(soldierDTO);
        soldier = soldierRepository.save(soldier);
        kieSession.insert(soldier);
        for (CreateWarDutyDTO warDutyDTO : soldierDTO.getWarDuties()) {
            WarDuty warDuty = new WarDuty(warDutyDTO);
            warDuty.setSoldier(soldier);
            warDuty = warDutyRepository.save(warDuty);
            kieSession.insert(warDuty);
        }
        for (CreateInjuryDTO injuryDTO : soldierDTO.getInjuries()) {
            Injury injury = new Injury(injuryDTO);
            injury.setSoldier(soldier);
            injury = injuryRepository.save(injury);
            kieSession.insert(injury);
        }

        kieSession.fireAllRules();
        return soldierRepository.save(soldier);
    }

    @Override
    public CreateSoldierDTO getOne(Long id) {
        Optional<Soldier> soldier = soldierRepository.findById(id);
        if (!soldier.isPresent()) return null;
        CreateSoldierDTO createSoldierDTO = new CreateSoldierDTO();
        createSoldierDTO.setFullName(soldier.get().getFullName());
        createSoldierDTO.setJmbg(soldier.get().getJmbg());
        
        List<CreateWarDutyDTO> warDutyDTOs = new ArrayList<>();
        List<WarDuty> warDuties = warDutyRepository.findBySoldierId(id);
        for (WarDuty warDuty : warDuties) {
            warDutyDTOs.add(new CreateWarDutyDTO(warDuty.getStartDate(), warDuty.getEndDate(), warDuty.getType().toString()));
        }
        createSoldierDTO.setWarDuties(warDutyDTOs);

        List<CreateInjuryDTO> injuryDTOs = new ArrayList<>();
        List<Injury> injuries = injuryRepository.findBySoldierId(id);
        for (Injury injury : injuries) {
            injuryDTOs.add(new CreateInjuryDTO(injury.getType().toString()));
        }
        createSoldierDTO.setInjuries(injuryDTOs);

        return createSoldierDTO;
    }
}