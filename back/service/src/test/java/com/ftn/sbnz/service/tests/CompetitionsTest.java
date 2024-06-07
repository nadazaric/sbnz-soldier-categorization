package com.ftn.sbnz.service.tests;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.ftn.sbnz.model.feature_competitions.models.Competitor;
import com.ftn.sbnz.model.feature_competitions.models.SpaCompetition;
import com.ftn.sbnz.model.feature_competitions.values.CompetitorStatus;
import com.ftn.sbnz.model.feature_competitions.values.FamilyType;
import com.ftn.sbnz.model.feature_soldiers.values.InjuryType;

public class CompetitionsTest {
    @Test
    public void competitionsTest() throws IOException {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("bwKsession");

        SpaCompetition sc0 = new SpaCompetition();
        sc0.setYear("2020");
        sc0.setIsDone(true);
        Competitor c0 = new Competitor( 
            "Dragomir Vasic",
            "4",
            CompetitorStatus.ACCEPTED,
            FamilyType.NONE,
            InjuryType.MEDIUM,
            423
        );
        Set<Competitor> competitors0 = new HashSet<>();
        competitors0.add(c0);
        sc0.setSelectedCompetitors(competitors0);

        SpaCompetition sc1 = new SpaCompetition();
        sc1.setYear("2023");
        sc1.setIsDone(true);
        Competitor c1 = new Competitor(
            "Dragana Mikic",
            "1",
            CompetitorStatus.ACCEPTED,
            FamilyType.NONE,
            InjuryType.HIGH,
            300
        );
        Competitor c2 = new Competitor(
            "Dorian Vebic",
            "2",
            CompetitorStatus.ACCEPTED,
            FamilyType.NONE,
            InjuryType.HIGH,
            300
        );
        Competitor c3 = new Competitor(
            "Filip Kokic",
            "3",
            CompetitorStatus.ACCEPTED,
            FamilyType.SPOUSE,
            InjuryType.HIGH,
            300
        );
        Set<Competitor> competitors1 = new HashSet<>();
        competitors1.add(c1);
        competitors1.add(c2);
        competitors1.add(c3);
        sc1.setSelectedCompetitors(competitors1);

        SpaCompetition sc2 = new SpaCompetition("2024", 2);
        Competitor c4 = new Competitor( 
            "Filip Kokic",
            "3",
            CompetitorStatus.WAITING,
            FamilyType.NONE,
            InjuryType.LOW,
            0
        );
        Competitor c5 = new Competitor( 
            "Dragomir Vasic",
            "4",
            CompetitorStatus.WAITING,
            FamilyType.SPOUSE,
            InjuryType.HIGH,
            0
        );
        Competitor c6 = new Competitor( 
            "Spasoje Gigic",
            "5",
            CompetitorStatus.WAITING,
            FamilyType.CHILD,
            InjuryType.HIGH,
            0
        );
        Competitor c7 = new Competitor( 
            "Helene Mikic",
            "6",
            CompetitorStatus.WAITING,
            FamilyType.CHILD,
            InjuryType.LOW,
            0
        );

        Set<Competitor> competitors2 = new HashSet<>();
        competitors2.add(c4);
        competitors2.add(c5);
        competitors2.add(c6);
        competitors2.add(c7);
        sc2.setCompetitors(competitors2);

        kieSession.insert(sc0);
        kieSession.insert(c0);
        kieSession.insert(sc1);
        kieSession.insert(sc2);
        kieSession.insert(c4);
        kieSession.insert(c5);
        kieSession.insert(c6);
        kieSession.insert(c7);
        kieSession.fireAllRules();
        System.out.println(c4.getStatus());
        System.out.println(c5.getStatus());
        System.out.println(c5.getScore());

        sc2.setIsDone(true);
        kieSession.update(kieSession.getFactHandle(sc2), sc2);
        kieSession.fireAllRules();

        for (Competitor competitor : sc2.getSelectedCompetitors()) {
            System.out.println("Competitor: " + competitor.getFullName() + ", Score: " + competitor.getScore());
        }

    }
}
