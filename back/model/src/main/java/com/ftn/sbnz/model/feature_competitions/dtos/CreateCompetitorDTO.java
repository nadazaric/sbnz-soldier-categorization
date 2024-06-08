package com.ftn.sbnz.model.feature_competitions.dtos;

import com.ftn.sbnz.model.feature_competitions.values.FamilyType;
import com.ftn.sbnz.model.feature_soldiers.values.InjuryType;

public class CreateCompetitorDTO {
    private Long competitionId;
    private String fullName;
    private String jmbg;
    private FamilyType deadFamilyMember;
    private InjuryType injuryType;

    public CreateCompetitorDTO() {
    }

    public CreateCompetitorDTO(Long competitionId, String fullName, String jmbg, FamilyType deadFamilyMember,
            InjuryType injuryType) {
        this.competitionId = competitionId;
        this.fullName = fullName;
        this.jmbg = jmbg;
        this.deadFamilyMember = deadFamilyMember;
        this.injuryType = injuryType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }   
}