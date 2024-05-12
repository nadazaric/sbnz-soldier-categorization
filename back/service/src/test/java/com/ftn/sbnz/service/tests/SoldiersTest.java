package com.ftn.sbnz.service.tests;

import java.io.InputStream;
import java.time.LocalDate;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import com.ftn.sbnz.model.feature_soldiers.values.SoldierCategory;
import com.ftn.sbnz.model.feature_soldiers.values.WarDutyType;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import java.util.List;
// import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.drools.template.DataProvider;

public class SoldiersTest {

    // @Test
    // public void generateTemplate() {
    //     InputStream template = SoldiersTest.class.getResourceAsStream("/templatetable/customer-classification-simple.drt");
    //     InputStream data = SoldiersTest.class.getResourceAsStream("/templatetable/template-data.xls");
        
    //     ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
    //     String drl = converter.compile(data, template, 3, 2);
        
    //     System.out.println(drl);
        
    //     KieSession ksession = this.createKieSessionFromDRL(drl);
        
    //     this.test(ksession);
    // }

    @Test
    public void generateTemplate() {
        // InputStream template = SoldiersTest.class.getResourceAsStream("/kjar/src/main/resources/rules/feature_soldiers/categorization.drt");
        InputStream template = SoldiersTest.class.getResourceAsStream("/feature_soldiers/categorization.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"0", "7", "I", "4"},
            new String[]{"7", "14", "II", "5"},
            new String[]{"14", "21", "III", "6"},
            new String[]{"21", "28", "IV", "7"},
            new String[]{"28", "35", "V", "8"},
            new String[]{"35", "40", "VI", "9"},
            new String[]{"40", "45", "VII", "10"},
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        System.err.println(template);
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
        
        test(ksession);
    }

    private void test(KieSession ksession) {
        // KieServices ks = KieServices.Factory.get();
        // KieContainer kContainer = ks.getKieClasspathContainer(); 
        // KieSession ksession = kContainer.newKieSession("feature_soldiers_key_ssesion");

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
            LocalDate.of(1992, 5, 12),
            WarDutyType.WAR_ZONE, 
            s1);
        WarDuty wd2 = new WarDuty(
            2L, 
            LocalDate.of(1992, 6, 1), 
            LocalDate.of(1992, 8, 1),
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

        ksession.insert(s1);
        ksession.insert(wd1);   
        ksession.insert(wd2); 
        ksession.insert(wd3);
        ksession.insert(s2);
        ksession.fireAllRules();
        System.err.println(s1.getCategory());
        System.err.println(s1.getMonthlyContribution());
        System.err.println(s2.getCategory());
        System.err.println(s2.getMonthlyContribution());
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
