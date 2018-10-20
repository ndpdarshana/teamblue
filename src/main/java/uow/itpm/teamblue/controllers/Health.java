/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uow.itpm.teamblue.model.HealthCheck;
import uow.itpm.teamblue.model.PlagiarismCheck;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.services.PlagiarismCheckerService;
import uow.itpm.teamblue.services.TranslationService;

/**
 * This is test class
 * TODO: Remove
 */
@RestController
@RequestMapping("/rest")
public class Health {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    TranslationService translationService;

    @Autowired
    private PlagiarismCheckerService plagiarismCheckerService;

    @GetMapping("/health")
    public HealthCheck check(){
        logger.debug("Helthy");
        HealthCheck h = new HealthCheck();
        h.setStatus("success");
        return h;
    }

    @GetMapping("/test")
    public HealthCheck translate(){
        Translate translate = new Translate();
        translate.setFromLanguage("en");
        translate.setToLanguage("fr");
        translate.setText("Hello world");
//        translationService.detectLanguage(translate);
        translationService.translate(translate);
        HealthCheck h = new HealthCheck();
        h.setStatus("Success");
        return h;
    }

    @GetMapping("/copy")
    public PlagiarismCheck testCopy(){
//        plagiarismCheckerService.loginToApi();
        PlagiarismCheck plagiarismCheck = new PlagiarismCheck();
        plagiarismCheck.setText("The man Peter Dutton hand-picked as the first Australian Border Force (ABF) " +
                "commissioner has divulged new details of the lengths the Minister's office went to in securing " +
                "a tourist visa for an Italian nanny.");
        plagiarismCheck = plagiarismCheckerService.checkText(plagiarismCheck);
        return plagiarismCheckerService.result(plagiarismCheck);
    }
}
