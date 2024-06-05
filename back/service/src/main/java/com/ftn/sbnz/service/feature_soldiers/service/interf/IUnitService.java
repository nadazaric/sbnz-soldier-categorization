package com.ftn.sbnz.service.feature_soldiers.service.interf;

import java.util.List;

import com.ftn.sbnz.model.feature_soldiers.dtos.UnitDTO;

public interface IUnitService {
    public List<UnitDTO> getBasicUnits();
    public void saveUnitForSoldier(String jmbg, Long unitId);
}
