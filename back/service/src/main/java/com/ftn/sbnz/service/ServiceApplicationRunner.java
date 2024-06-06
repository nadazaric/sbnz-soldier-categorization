package com.ftn.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationRunner implements ApplicationRunner {
    
    @Autowired private IKieSessionService kieSesionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        kieSesionService.loadData();
    }
}
