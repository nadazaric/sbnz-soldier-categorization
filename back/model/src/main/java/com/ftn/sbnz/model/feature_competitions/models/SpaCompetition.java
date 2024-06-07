package com.ftn.sbnz.model.feature_competitions.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SpaCompetition {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String year;

    @Column 
    private Boolean isDone;

    @Column
    private Integer positionNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Competitor> competitors;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Competitor> selectedCompetitors;

    public SpaCompetition() {
        this.competitors = new HashSet<>();
        this.selectedCompetitors = new HashSet<>();
    }

    public SpaCompetition(String year, Integer positionNumber) {
        this.year = year;
        this.positionNumber = positionNumber;
        this.competitors = new HashSet<>();
        this.selectedCompetitors = new HashSet<>();
    }    

    public SpaCompetition(String name, String year, Integer positionNumber) {
        this.name = name;
        this.year = year;
        this.positionNumber = positionNumber;
        this.competitors = new HashSet<>();
        this.selectedCompetitors = new HashSet<>();
    }

    public SpaCompetition(Long id, String year, Boolean isDone, Integer positionNumber, Set<Competitor> competitors,
            Set<Competitor> selectedCompetitors) {
        this.id = id;
        this.year = year;
        this.isDone = isDone;
        this.positionNumber = positionNumber;
        this.competitors = competitors;
        this.selectedCompetitors = selectedCompetitors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
    }

    public Set<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Set<Competitor> competitors) {
        this.competitors = competitors;
    }

    public Set<Competitor> getSelectedCompetitors() {
        return selectedCompetitors;
    }

    public void setSelectedCompetitors(Set<Competitor> selectedCompetitors) {
        this.selectedCompetitors = selectedCompetitors;
    }
}