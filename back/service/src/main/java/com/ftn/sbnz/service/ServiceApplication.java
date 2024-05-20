package com.ftn.sbnz.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.*") 
@ComponentScan(basePackages = { "com.*" }) 
@EntityScan("com.*")
public class ServiceApplication  { 
	public static void main(String[] args) {
		System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog","fatal");
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public KieSession kieSession() throws IOException {
        // load template
        InputStream template = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/categorization.drt");
        InputStream data = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/categorization-values.xls");
        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);

        // load soldier rules
        InputStream ordinaryRules = ServiceApplication.class.getResourceAsStream("/rules/feature_soldiers/soldiers.drl");
        byte[] ordinaryRulesBytes = IOUtils.toByteArray(ordinaryRules);
        drl += new String(ordinaryRulesBytes, StandardCharsets.UTF_8);
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


	// @Bean
	// public KieContainer kieContainer() {
	// 	KieServices ks = KieServices.Factory.get();
	// 	KieContainer kContainer = ks
	// 			.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
	// 	KieScanner kScanner = ks.newKieScanner(kContainer);
	// 	kScanner.start(1000);
	// 	return kContainer;
	// }
	
	/*
	 * KieServices ks = KieServices.Factory.get(); KieContainer kContainer =
	 * ks.newKieContainer(ks.newReleaseId("drools-spring-v2",
	 * "drools-spring-v2-kjar", "0.0.1-SNAPSHOT")); KieScanner kScanner =
	 * ks.newKieScanner(kContainer); kScanner.start(10_000); KieSession kSession =
	 * kContainer.newKieSession();
	 */
}
