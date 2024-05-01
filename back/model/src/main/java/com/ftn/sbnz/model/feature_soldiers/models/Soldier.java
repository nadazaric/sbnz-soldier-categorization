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
}
