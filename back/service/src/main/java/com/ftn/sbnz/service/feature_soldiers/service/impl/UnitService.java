package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ftn.sbnz.model.core.Trigger;
import com.ftn.sbnz.model.feature_soldiers.dtos.UnitDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.Unit;
import com.ftn.sbnz.model.feature_soldiers.values.UnitType;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import com.ftn.sbnz.service.core.values.KieSessionAgendas;
import com.ftn.sbnz.service.feature_soldiers.repository.UnitRepository;
import com.ftn.sbnz.service.feature_soldiers.service.interf.IUnitService;

@Service
public class UnitService implements IUnitService{

    @Autowired private UnitRepository unitRepository;
    @Autowired private IKieSessionService kieSessionService;

    @Override
    public List<UnitDTO> getBasicUnits() {
        List<Unit> basicUnits = unitRepository.findByType(UnitType.UNIT);
        List<UnitDTO> unitDTOs = new ArrayList<>();
        for (Unit unit : basicUnits) {
            unitDTOs.add(new UnitDTO(unit.getId(), unit.getName()));
        }
        return unitDTOs;
    }

    @Override
    public void saveUnitForSoldier(String jmbg, Long parentUnitId) {
        Optional<Unit> unit = unitRepository.findById(parentUnitId);
        if (!unit.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent id " + parentUnitId + " is not valid.");
        Unit newUnit = new Unit(jmbg, unit.get(), UnitType.SOLDIER);
        kieSessionService.insertObject(newUnit);
        unitRepository.save(newUnit);
    }

    @Override
    public List<Soldier> getSoldiersForUnit(Long unitId) {
        Optional<Unit> unit = unitRepository.findById(unitId);
        if (!unit.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unit id " + unitId + " is not valid.");
        Trigger trigger = new Trigger(unit.get().getName());
        List<Soldier> soldiers = new ArrayList<>();
        kieSessionService.insertObject(trigger);
        kieSessionService.setGlobalObject("soldiersInUnit", soldiers);
        kieSessionService.fireRulesForAgenda(KieSessionAgendas.UNITS_AGENDA);
        return soldiers;
    }

    @Override
    public List<UnitDTO> getAllUnitsExcepSoldier() {
        List<Unit> units = unitRepository.findByType(UnitType.OTHER);
        units.addAll(unitRepository.findByType(UnitType.UNIT));
        List< UnitDTO> unitDTOs = new ArrayList<>();
        for (Unit unit : units) unitDTOs.add(new UnitDTO(unit.getId(), unit.getName()));
        return unitDTOs;
    }
    
}