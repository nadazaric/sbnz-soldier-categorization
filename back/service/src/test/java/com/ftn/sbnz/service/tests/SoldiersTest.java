package com.ftn.sbnz.service.tests;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import org.junit.Test;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import com.ftn.sbnz.model.feature_soldiers.values.SoldierCategory;
import com.ftn.sbnz.model.feature_soldiers.values.WarDutyType;

import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import java.util.List;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.apache.commons.io.IOUtils;


public class SoldiersTest {

    @Test
    public void generateTemplate() throws IOException {
        InputStream template = SoldiersTest.class.getResourceAsStream("/rules/feature_soldiers/categorization.drt");
        InputStream data = SoldiersTest.class.getResourceAsStream("/rules/feature_soldiers/categorization-values.xls");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);
        // System.err.println(drl);

        InputStream ordinaryRules = SoldiersTest.class.getResourceAsStream("/rules/feature_soldiers/soldiers.drl");
        byte[] ordinaryRulesBytes = IOUtils.toByteArray(ordinaryRules);
        drl += new String(ordinaryRulesBytes, StandardCharsets.UTF_8);
        // System.err.println(drl);

        KieSession ksession = this.createKieSessionFromDRL(drl);
        this.test(ksession);
    }

    private void test(KieSession ksession) {
        // KieServices ks = KieServices.Factory.get();
        // KieContainer kContainer = ks.getKieClasspathContainer(); 
        // KieSession ksessionn = kContainer.newKieSession("feature_soldiers_key_ssesion");

        Soldier s1 = new Soldier(
            1L, 
            "Matko Maric", 
            "1", 
            null, 
            SoldierCategory.UNCATEGORIZED, 
            0.0);
        WarDuty wd1 = new WarDuty(
            1L, 
            LocalDate.of(1992, 5, 1), 
            LocalDate.of(1992, 5, 31),
            WarDutyType.WAR_ZONE, 
            s1);
        WarDuty wd2 = new WarDuty(
            2L, 
            LocalDate.of(1992, 6, 1), 
            LocalDate.of(1992, 6, 29),
            WarDutyType.WAR_ZONE, 
            s1);
        WarDuty wd3 = new WarDuty(
            3L, 
            LocalDate.of(1993, 6, 1), 
            LocalDate.of(1993, 12, 1),
            WarDutyType.WORK_DUTY, 
            s1);
        
        Soldier s2 = new Soldier(
            2L, 
            "Nemanja Momic", 
            "2", 
            null, 
            SoldierCategory.UNCATEGORIZED, 
            0.0);
        
        Soldier s3 = new Soldier(
            3L, 
            "Bogdan Cicic", 
            "2", 
            13, 
            SoldierCategory.UNCATEGORIZED, 
            0.0);

        ksession.insert(s1);
        ksession.insert(wd1);   
        ksession.insert(wd2); 
        ksession.insert(wd3);
        ksession.insert(s2);
        ksession.insert(s3);
        ksession.fireAllRules();
        System.err.println(s1.getFullName());
        System.err.println(s1.getMonths());
        System.err.println(s1.getCategory());
        System.err.println(s1.getMonthlyContribution());
        System.err.println(s2.getFullName());
        System.err.println(s2.getMonths());
        System.err.println(s2.getCategory());
        System.err.println(s2.getMonthlyContribution());
        System.err.println(s3.getFullName());
        System.err.println(s3.getMonths());
        System.err.println(s3.getCategory());
        System.err.println(s3.getMonthlyContribution());
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
