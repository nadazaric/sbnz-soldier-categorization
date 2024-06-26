package com.ftn.sbnz.service.feature_soldiers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {
    Optional<Soldier> findByJmbg(String jmbg);
}
