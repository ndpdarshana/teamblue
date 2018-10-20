/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Copyleaks request object for submission job
 */
public class PlagiarismCheck {
    private String text;
    private String language;
    @JsonProperty("ProcessId")
    private String processId;
    private PlagiarismApiStatus plagiarismApiStatus;
    private List<PlagiarismApiResult> plagiarismApiResultList;
    private String key;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public PlagiarismApiStatus getPlagiarismApiStatus() {
        return plagiarismApiStatus;
    }

    public void setPlagiarismApiStatus(PlagiarismApiStatus plagiarismApiStatus) {
        this.plagiarismApiStatus = plagiarismApiStatus;
    }

    public List<PlagiarismApiResult> getPlagiarismApiResultList() {
        return plagiarismApiResultList;
    }

    public void setPlagiarismApiResultList(List<PlagiarismApiResult> plagiarismApiResultList) {
        this.plagiarismApiResultList = plagiarismApiResultList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
