package com.ftn.sbnz.model.feature_competitions.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ftn.sbnz.model.feature_competitions.values.CompetitorStatus;
import com.ftn.sbnz.model.feature_competitions.values.FamilyType;
import com.ftn.sbnz.model.feature_soldiers.values.InjuryType;

@Entity
public class Competitor {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private LocalDate birthDate;

    @Column
    private CompetitorStatus status;

    @Column
    private FamilyType deadFamilyMember;

    @Column
    private InjuryType injuryType;

    public Competitor() {}

    public Competitor(Long id, String fullName, LocalDate birthDate, CompetitorStatus status,
            FamilyType deadFamilyMember, InjuryType injuryType) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.status = status;
        this.deadFamilyMember = deadFamilyMember;
        this.injuryType = injuryType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CompetitorStatus getStatus() {
        return status;
    }

    public void setStatus(CompetitorStatus status) {
        this.status = status;
    }

    public FamilyType getDeadFamilyMember() {
        return deadFamilyMember;
    }

    public void setDeadFamilyMember(FamilyType deadFamilyMember) {
        this.deadFamilyMember = deadFamilyMember;
    }

    public InjuryType getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(InjuryType injuryType) {
        this.injuryType = injuryType;
    }
}