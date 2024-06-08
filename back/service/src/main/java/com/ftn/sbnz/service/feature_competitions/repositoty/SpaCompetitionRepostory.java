package com.ftn.sbnz.service.feature_competitions.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;

@Repository
public interface SpaCompetitionRepostory extends JpaRepository<SpaCompetition, Long> {
    public List<SpaCompetition> findByYear(Integer year);
}
