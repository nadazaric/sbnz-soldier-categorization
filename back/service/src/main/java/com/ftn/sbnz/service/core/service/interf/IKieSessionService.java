package com.ftn.sbnz.service.core.service.interf;

import org.kie.api.runtime.KieSession;

public interface IKieSessionService {
    public KieSession getKieSession();
    public void loadData();
    public void fireRulesForAgenda(String agenda);
    public void insertObject(Object object);
}
