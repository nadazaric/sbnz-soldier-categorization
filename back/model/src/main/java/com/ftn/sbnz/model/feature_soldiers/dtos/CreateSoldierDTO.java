package com.ftn.sbnz.model.feature_soldiers.dtos;

public class CreateSoldierDTO {
    
    private String fullName;
    private String jmbg;

    public CreateSoldierDTO() {}
    
    public CreateSoldierDTO(String fullName, String jmbg) {
        this.fullName = fullName;
        this.jmbg = jmbg;
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
}