package com.ftn.sbnz.service.core.service.interf;

import org.kie.api.runtime.KieSession;

public interface IKieSessionService {
    public KieSession getKieSession();
    public void loadData();
}
