/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.model.TranslatorProperties;
import uow.itpm.teamblue.model.TranslatorResponse;
import uow.itpm.teamblue.module.translator.TranslatorApi;

/**
 * TranslationService route the translation service requests to the translation module.
 */
@Service
public class TranslationService {
    @Autowired
    private TranslatorApi translatorApi;
    @Autowired
    private TranslatorProperties translatorProperties;

    /**
     * Detect language router
     * @param translate
     * @return
     */
    public TranslatorResponse detectLanguage(Translate translate){
        return translatorApi.getLanguage(translate);
    }

    /**
     * Translation job router
     * @param translate
     * @return
     */
    public TranslatorResponse translate(Translate translate){
        TranslatorResponse response = translatorApi.getTranslated(translate);
        System.out.println(response.getLang());
        System.out.println(response.getCode());
        System.out.println(response.getText());
        return response;
    }
}
