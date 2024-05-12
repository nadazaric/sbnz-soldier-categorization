package com.ftn.sbnz.service.feature_competitions.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ftn.sbnz.model.feature_competitions.models.Competitor;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
    
}
