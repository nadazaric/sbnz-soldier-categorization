package com.ftn.sbnz.service.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.ftn.sbnz.model.core.Trigger;
import com.ftn.sbnz.model.feature_soldiers.models.Soldier;
import com.ftn.sbnz.model.feature_soldiers.models.Unit;
import com.ftn.sbnz.model.feature_soldiers.values.SoldierCategory;
import com.ftn.sbnz.model.feature_soldiers.values.SoldierStatus;

public class BackwardTest {
    
    @Test
    public void backwardTest() throws IOException {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("bwKsession");

        Unit u1 = new Unit(1L, "Armija", null);

        Unit u2 = new Unit(2L, "I Brigada", u1);
        Unit u3 = new Unit(3L, "II Brigada", u1);

        Unit u4 = new Unit(4L, "I Bataljon", u2);
        Unit u5 = new Unit(5L, "II Bataljon", u2);
        Unit u6 = new Unit(6L, "III Bataljon", u3);
        Unit u7 = new Unit(7L, "IV Bataljon", u3);

        Unit u8 = new Unit(8L, "I Jedinica", u4);
        Unit u9 = new Unit(9L, "II Jedinica", u4);
        Unit u10 = new Unit(10L, "III Jedinica", u5);
        Unit u11 = new Unit(11L, "IV Jedinica", u5);
        Unit u12 = new Unit(12L, "V Jedinica", u6);
        Unit u13 = new Unit(13L, "VI Jedinica", u6);
        Unit u14 = new Unit(14L, "VII Jedinica", u7);
        Unit u15 = new Unit(15L, "VIII Jedinica", u7);

       Soldier s1 = new Soldier(
            1L, 
            "Matko Maric", 
            "1", 
            null, 
            SoldierCategory.NONE, 
            0.0,
            SoldierStatus.NO_MONTHS
        );

        Soldier s2 = new Soldier(
            1L, 
            "Danica Mikic", 
            "2", 
            null, 
            SoldierCategory.NONE, 
            0.0,
            SoldierStatus.NO_MONTHS
        );

        Soldier s3 = new Soldier(
            1L, 
            "Danilo Babkic", 
            "3", 
            null, 
            SoldierCategory.NONE, 
            0.0,
            SoldierStatus.NO_MONTHS
        );

        Soldier s4 = new Soldier(
            1L, 
            "Dunja Kikic", 
            "4", 
            null, 
            SoldierCategory.NONE, 
            0.0,
            SoldierStatus.NO_MONTHS
        );

        Unit u16 = new Unit(16L, s1.getJmbg(), u8);
        Unit u17 = new Unit(17L, s2.getJmbg(), u10);
        Unit u18 = new Unit(18L, s3.getJmbg(), u15);
        Unit u19 = new Unit(18L, s4.getJmbg(), u8);
        Unit u20 = new Unit(18L, s4.getJmbg(), u14);

        kieSession.insert(u1);
        kieSession.insert(u2);
        kieSession.insert(u3);
        kieSession.insert(u4);
        kieSession.insert(u5);
        kieSession.insert(u6);
        kieSession.insert(u7);
        kieSession.insert(u8);
        kieSession.insert(u9);
        kieSession.insert(u10);
        kieSession.insert(u11);
        kieSession.insert(u12);
        kieSession.insert(u13);
        kieSession.insert(u14);
        kieSession.insert(u15);
        kieSession.insert(u16);
        kieSession.insert(u17);
        kieSession.insert(u18);
        kieSession.insert(u19);
        kieSession.insert(u20);
        kieSession.insert(s1);
        kieSession.insert(s2);
        kieSession.insert(s3);
        kieSession.insert(s4);
        kieSession.insert(new Trigger("Armija"));

        List<Soldier> soldiersInUnit = new ArrayList<>();
        kieSession.setGlobal("soldiersInUnit", soldiersInUnit);
        kieSession.fireAllRules();
        for (Soldier soldier : soldiersInUnit) {
            System.err.println("Soldier in unit: " + soldier.getFullName());
        }
    }
}
