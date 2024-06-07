package com.ftn.sbnz.model.feature_competitions.dtos;

public class CreateSpaCompetitionDTO {
    private String name;
    private Integer positionNumber;

    public CreateSpaCompetitionDTO() {
    }
    
    public CreateSpaCompetitionDTO(String name, Integer positionNumber) {
        this.name = name;
        this.positionNumber = positionNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
    }   
}