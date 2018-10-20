/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Property configuration reads and store property file contents starting with prefix of translator.
 */
@ConfigurationProperties(prefix = "translator")
public class TranslatorProperties {
    private String apikey;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApikey() {

        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
