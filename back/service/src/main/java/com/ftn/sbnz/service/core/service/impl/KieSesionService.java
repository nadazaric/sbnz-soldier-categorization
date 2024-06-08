package com.ftn.sbnz.service.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.Unit;
import com.ftn.sbnz.service.ServiceApplication;
import com.ftn.sbnz.service.core.service.interf.IKieSessionService;
import com.ftn.sbnz.service.feature_soldiers.repository.SoldierRepository;
import com.ftn.sbnz.service.feature_soldiers.repository.UnitRepository;

@Service
public class KieSesionService implements IKieSessionService{

    private final KieSession kieSession;
    @Autowired private SoldierRepository soldierRepository;
    @Autowired private UnitRepository unitRepository;

    @Autowired
    public KieSesionService() throws IOException {
        this.kieSession = generateKieSession();
    }

    @Override
    public KieSession getKieSession() {
        return kieSession;
    }

    @Override
    public void loadData() {
        List<Soldier> soldiers = soldierRepository.findAll();
        for (Soldier soldier : soldiers) kieSession.insert(soldier);
        List<Unit> units = unitRepository.findAll();
        for (Unit unit : units) kieSession.insert(unit);
    }

    @Override
    public void fireRulesForAgenda(String agenda) {
        this.kieSession.getAgenda().getAgendaGroup(agenda).setFocus();
        this.kieSession.fireAllRules();
    }

    @Override
    public void insertObject(Object object) {
        this.kieSession.insert(object);
    }

    @Override
    public void setGlobalObject(String globalName, Object object) {
        kieSession.setGlobal(globalName, object);
    }

    private KieSession generateKieSession() throws IOException {
        // load template
        InputStream template = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/categorization.drt");
        InputStream data = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/categorization-values.xls");
        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);

        // load soldier rules
        InputStream soldierRules = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/soldiers.drl");
        byte[] soldierRulesBytes = IOUtils.toByteArray(soldierRules);
        drl += new String(soldierRulesBytes, StandardCharsets.UTF_8);

        // load unit rules
        InputStream unitRules = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/soldierUnits.drl");
        byte[] unitRulesBytes = IOUtils.toByteArray(unitRules);
        drl += new String(unitRulesBytes, StandardCharsets.UTF_8);

        // load competition rules
        InputStream competitionRules = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/competitions.drl");
        byte[] competitionRulesBytes = IOUtils.toByteArray(competitionRules);
        drl += new String(competitionRulesBytes, StandardCharsets.UTF_8);
        // System.err.println(drl);

        return this.createKieSessionFromDRL(drl);
    }

	private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
}