package com.ftn.sbnz.service.feature_soldiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import org.springframework.stereotype.Repository;

@Repository
public interface WarDutyRepository extends JpaRepository<WarDuty, Long> {
    
}
