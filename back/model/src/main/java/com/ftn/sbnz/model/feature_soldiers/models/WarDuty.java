package com.ftn.sbnz.model.feature_soldiers.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.ftn.sbnz.model.feature_soldiers.values.WarDutyType;

@Entity
public class WarDuty {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private WarDutyType type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Soldier soldier;

    public WarDuty() {}

    public WarDuty(Long id, LocalDate startDate, LocalDate endDate, WarDutyType type, Soldier soldier) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.soldier = soldier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public WarDutyType getType() {
        return type;
    }

    public void setType(WarDutyType type) {
        this.type = type;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }   
}