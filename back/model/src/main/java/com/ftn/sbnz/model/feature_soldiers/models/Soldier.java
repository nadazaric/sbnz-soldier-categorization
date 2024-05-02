package com.ftn.sbnz.model.feature_soldiers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ftn.sbnz.model.feature_soldiers.values.SoldierCategory;

@Entity
public class Soldier {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private String jmbg;

    @Column
    private Integer months;

    @Column
    private SoldierCategory category;

    @Column
    private Double monthlyContribution;

    public Soldier() {}

    public Soldier(Long id, String fullName, String jmbg, Integer months, SoldierCategory category,
            Double monthlyContribution) {
        this.id = id;
        this.fullName = fullName;
        this.jmbg = jmbg;
        this.months = months;
        this.category = category;
        this.monthlyContribution = monthlyContribution;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public SoldierCategory getCategory() {
        return category;
    }

    public void setCategory(SoldierCategory category) {
        this.category = category;
    }

    public Double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(Double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }
}
