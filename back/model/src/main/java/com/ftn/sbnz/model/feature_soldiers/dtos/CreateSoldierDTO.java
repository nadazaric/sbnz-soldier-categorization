package com.ftn.sbnz.model.feature_soldiers.dtos;

import java.util.List;

public class CreateSoldierDTO {
    
    private String fullName;
    private String jmbg;
    private List<CreateWarDutyDTO> warDuties;
    private List<CreateInjuryDTO> injuries;
    private List<Long> units;

    public CreateSoldierDTO() {}

    public CreateSoldierDTO(String fullName, String jmbg, List<CreateWarDutyDTO> warDuties,
            List<CreateInjuryDTO> injuries, List<Long> units) {
        this.fullName = fullName;
        this.jmbg = jmbg;
        this.warDuties = warDuties;
        this.injuries = injuries;
        this.units = units;
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
    public List<CreateWarDutyDTO> getWarDuties() {
        return warDuties;
    }
    public void setWarDuties(List<CreateWarDutyDTO> warDuties) {
        this.warDuties = warDuties;
    }
    public List<CreateInjuryDTO> getInjuries() {
        return injuries;
    }
    public void setInjuries(List<CreateInjuryDTO> injuries) {
        this.injuries = injuries;
    }
    public List<Long> getUnits() {
        return units;
    }
    public void setUnits(List<Long> units) {
        this.units = units;
    }
}