package com.ftn.sbnz.model.feature_soldiers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.ftn.sbnz.model.feature_soldiers.dtos.CreateInjuryDTO;
import com.ftn.sbnz.model.feature_soldiers.values.InjuryType;

@Entity
public class Injury {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private InjuryType type;

    public Injury() {}

    @ManyToOne(fetch = FetchType.EAGER)
    private Soldier soldier;

    public Injury(Long id, InjuryType type) {
        this.id = id;
        this.type = type;
    }

    public Injury(Long id, InjuryType type, Soldier soldier) {
        this.id = id;
        this.type = type;
        this.soldier = soldier;
    }

    public Injury(CreateInjuryDTO injuryDTO) {
        this.type = InjuryType.valueOf(injuryDTO.getType());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InjuryType getType() {
        return type;
    }

    public void setType(InjuryType type) {
        this.type = type;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }
    
}
