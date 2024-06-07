package com.ftn.sbnz.model.feature_competitions.models;

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

    @Column(unique = true)
    private String jmbg;

    @Column
    private CompetitorStatus status;

    @Column
    private FamilyType deadFamilyMember;

    @Column
    private InjuryType injuryType;

    @Column
    private Integer score;

    
    public Competitor() {
    }

    public Competitor(String fullName, String jmbg, FamilyType deadFamilyMember, InjuryType injuryType) {
        this.fullName = fullName;
        this.jmbg = jmbg;
        this.deadFamilyMember = deadFamilyMember;
        this.injuryType = injuryType;
    }

    public Competitor(String fullName, String jmbg, CompetitorStatus status,
            FamilyType deadFamilyMember, InjuryType injuryType, Integer score) {
        this.fullName = fullName;
        this.jmbg = jmbg;
        this.status = status;
        this.deadFamilyMember = deadFamilyMember;
        this.injuryType = injuryType;
        this.score = score;
    }

    public Competitor(Long id, String fullName, String jmbg, CompetitorStatus status,
            FamilyType deadFamilyMember, InjuryType injuryType, Integer score) {
        this.id = id;
        this.fullName = fullName;
        this.jmbg = jmbg;
        this.status = status;
        this.deadFamilyMember = deadFamilyMember;
        this.injuryType = injuryType;
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }    
}