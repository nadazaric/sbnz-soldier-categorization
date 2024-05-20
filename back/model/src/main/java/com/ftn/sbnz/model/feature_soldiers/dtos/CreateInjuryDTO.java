package com.ftn.sbnz.model.feature_soldiers.dtos;

public class CreateInjuryDTO {
    private String type;

    public CreateInjuryDTO() {
    }

    public CreateInjuryDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}