package com.ftn.sbnz.service.feature_soldiers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.feature_soldiers.dtos.UnitDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Unit;
import com.ftn.sbnz.model.feature_soldiers.values.UnitType;
import com.ftn.sbnz.service.feature_soldiers.repository.UnitRepository;
import com.ftn.sbnz.service.feature_soldiers.service.interf.IUnitService;

@Service
public class UnitService implements IUnitService{

    @Autowired private UnitRepository unitRepository;

    @Override
    public List<UnitDTO> getBasicUnits() {
        List<Unit> basicUnits = unitRepository.findByType(UnitType.UNIT);
        List<UnitDTO> unitDTOs = new ArrayList<>();
        for (Unit unit : basicUnits) {
            unitDTOs.add(new UnitDTO(unit.getId(), unit.getName()));
        }
        return unitDTOs;
    }
    
}
