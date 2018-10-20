/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Copyleaks response for status check
 */
public class PlagiarismApiStatus {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("ProgressPercents")
    private Integer ProgressPercents;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgressPercents() {
        return ProgressPercents;
    }

    public void setProgressPercents(Integer progressPercents) {
        ProgressPercents = progressPercents;
    }
}
