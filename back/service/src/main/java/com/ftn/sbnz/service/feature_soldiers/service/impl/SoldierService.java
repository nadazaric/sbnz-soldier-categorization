package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ftn.sbnz.model.feature_soldiers.dtos.CreateInjuryDTO;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateSoldierDTO;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateWarDutyDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Injury;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.Unit;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import com.ftn.sbnz.service.core.values.KieSessionAgendas;
import com.ftn.sbnz.service.feature_soldiers.repository.InjuryRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.SoldierRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.UnitRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.WarDutyRepository;
import com.ftn.sbnz.service.feature_soldiers.service.interf.ISoldierService;

@Service
public class SoldierService implements ISoldierService {

    @Autowired SoldierRepository soldierRepository;
    @Autowired WarDutyRepository warDutyRepository;
    @Autowired InjuryRepository injuryRepository;
    @Autowired IKieSessionService kieSessionService;
    @Autowired UnitRepository unitRepository;

    @Override
    public List<Soldier> getAll() {
        return soldierRepository.findAll();
    }

    @Override
    public Soldier saveSoldier(CreateSoldierDTO soldierDTO) {
        Optional<Soldier> alreadyExists = soldierRepository.findByJmbg(soldierDTO.getJmbg());
        if (alreadyExists.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Soldier with jmbg " + soldierDTO.getJmbg() + " already exists.");

        Soldier soldier = new Soldier(soldierDTO);
        soldier = soldierRepository.save(soldier);
        kieSessionService.insertObject(soldier);
        for (CreateWarDutyDTO warDutyDTO : soldierDTO.getWarDuties()) {
            WarDuty warDuty = new WarDuty(warDutyDTO);
            warDuty.setSoldier(soldier);
            warDuty = warDutyRepository.save(warDuty);
            kieSessionService.insertObject(warDuty);
        }
        for (CreateInjuryDTO injuryDTO : soldierDTO.getInjuries()) {
            Injury injury = new Injury(injuryDTO);
            injury.setSoldier(soldier);
            injury = injuryRepository.save(injury);
            kieSessionService.insertObject(injury);
        }

        kieSessionService.fireRulesForAgenda(KieSessionAgendas.CATEGORIZATION_AGENDA);
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

        List<Unit> units = unitRepository.findByName(soldier.get().getJmbg());
        List<Long> unitIds = new ArrayList<>();
        for (Unit unit : units) unitIds.add(unit.getParent().getId());
        createSoldierDTO.setUnits(unitIds);

        return createSoldierDTO;
    }
}