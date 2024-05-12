package com.ftn.sbnz.service.tests;

import java.time.LocalDate;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.WarDuty;
import com.ftn.sbnz.model.feature_soldiers.values.SoldierCategory;
import com.ftn.sbnz.model.feature_soldiers.values.WarDutyType;

public class SoldiersTest {

    @Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer(); 
        KieSession ksession = kContainer.newKieSession("feature_soldiers_key_ssesion");

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

        ksession.insert(s1);
        ksession.insert(wd1);   
        ksession.insert(wd2); 
        ksession.insert(wd3);
        ksession.fireAllRules();
        System.err.println(s1.getMonths());
    }
}
