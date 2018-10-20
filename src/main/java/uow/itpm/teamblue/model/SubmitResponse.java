/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import java.util.List;

/**
 * SubmitResponse has many use over the application.
 * This holds the document details and copyleaks results.
 * This will convert into json object and return to the UI
 */
public class SubmitResponse {
    private Integer docId;
    private String status;
    private String message;
    private String docName;
    private List<PlagiarismResult> plagiarismCheck;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public List<PlagiarismResult> getPlagiarismCheck() {
        return plagiarismCheck;
    }

    public void setPlagiarismCheck(List<PlagiarismResult> plagiarismCheck) {
        this.plagiarismCheck = plagiarismCheck;
    }
}
