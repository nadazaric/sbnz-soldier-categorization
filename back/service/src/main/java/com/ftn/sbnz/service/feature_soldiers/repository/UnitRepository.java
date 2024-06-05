package com.ftn.sbnz.service.feature_soldiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.feature_soldiers.models.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
