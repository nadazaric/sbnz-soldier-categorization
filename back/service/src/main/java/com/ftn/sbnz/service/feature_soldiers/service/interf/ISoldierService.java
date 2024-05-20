package com.ftn.sbnz.service.feature_soldiers.service.interf;

import java.util.List;

import com.ftn.sbnz.model.feature_soldiers.dtos.CreateSoldierDTO;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;

public interface ISoldierService {
    public List<Soldier> getAll();
    public Soldier saveSoldier(CreateSoldierDTO soldierDTO);
    public CreateSoldierDTO getOne(Long id);
}
