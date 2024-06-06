package com.ftn.sbnz.service.feature_soldiers.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.sbnz.model.feature_soldiers.models.Unit;
import com.ftn.sbnz.model.feature_soldiers.values.UnitType;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    public List<Unit> findByType(UnitType type);
    public List<Unit> findByName(String name);
}
