package com.ftn.sbnz.model.feature_soldiers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import org.kie.api.definition.type.Position;

@Entity
public class Unit {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Position(0)
    private String name;

    @Column
    @Position(1)
    private String parentName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Unit parent;
    
    public Unit() {
    }

    public Unit(String name, Unit parent) {
        this.name = name;
        this.parent = parent;
        if (parent == null) return;
        this.parentName = parent.getName();
    }

    public Unit(Long id, String name, Unit parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        if (parent == null) return;
        this.parentName = parent.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        if (parent == null) return null;
        return parent.getName();
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Unit getParent() {
        return parent;
    }

    public void setParent(Unit parent) {
        this.parent = parent;
    }   
}