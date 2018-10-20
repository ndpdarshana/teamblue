/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.module.translator.yandex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.model.TranslatorProperties;
import uow.itpm.teamblue.model.TranslatorResponse;
import uow.itpm.teamblue.module.translator.TranslatorApi;

import java.util.Arrays;

/**
 * YandexTranslator class contains the implementation of Translation api request and response management.
 */
public class YandexTranslator implements TranslatorApi {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TranslatorProperties translatorProperties;

    /**
     * This method used to retrieve the language of given text.
     * @param translate
     * @return TranslatorResponse
     */
    @Override
    public TranslatorResponse getLanguage(Translate translate) {
        String url = translatorProperties.getUrl() + "/detect?hint=en,de&key=" + translatorProperties.getApikey();
        return getResponse(url, translate);
    }

    /**
     * This method send the translation request and wait for the result. Usually it takes couple of seconds to return the result.
     * Return results contain translated text.
     * @param translate
     * @return TranslatorResponse
     */
    @Override
    public TranslatorResponse getTranslated(Translate translate) {
        String url = translatorProperties.getUrl() + "/translate?lang=" + translate.getFromLanguage()
                + "-" + translate.getToLanguage() + "&key="
                + translatorProperties.getApikey();

        return getResponse(url, translate);
    }

    /**
     * Generic method to connect with translator API with custom URLs.
     * @param url
     * @param translate
     * @return
     */
    private TranslatorResponse getResponse(String url, Translate translate){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentLength(translate.getText().length());
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        String body = "text=" + translate.getText();
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url, request, TranslatorResponse.class);
    }
}
