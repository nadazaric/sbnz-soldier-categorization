package com.ftn.sbnz.service.feature_soldiers.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.sbnz.model.feature_soldiers.models.Injury;

import org.springframework.stereotype.Repository;

@Repository
public interface InjuryRepository extends JpaRepository<Injury, Long> {
    
    public List<Injury> findBySoldierId(Long soldierId);
}
