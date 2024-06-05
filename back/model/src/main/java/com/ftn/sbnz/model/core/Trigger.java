package com.ftn.sbnz.model.core;

public class Trigger {
    private String searchParam;
  
    public Trigger() {
    }

    public Trigger(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }
    
}
