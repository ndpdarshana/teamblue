/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import javax.persistence.*;

/**
 * Database entity model for CopyLeaksMetadata
 * This class creates the database table CopyLeaksMetada if not available and used to do all the transactions with database
 */
@Entity
public class CopyLeaksMetadata {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id; //MySQL Auto generated Id field

    @OneToOne
    @JoinColumn(name = "doc_id")
    private Document document; //Associated Document object contains document details

    private String copyLeaksId; //Copyleaks generated ID which used to retrieve results

    private String status; //Status of the document process

    @Column(length = 1000)
    private String result;

    private String lang; //Document language

    private String readOnlyKey; //Copyleaks generated key to access result page

    private String resultUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "id")
    @JoinColumn(name = "doc_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getCopyLeaksId() {
        return copyLeaksId;
    }

    public void setCopyLeaksId(String copyLeaksId) {
        this.copyLeaksId = copyLeaksId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getReadOnlyKey() {
        return readOnlyKey;
    }

    public void setReadOnlyKey(String readOnlyKey) {
        this.readOnlyKey = readOnlyKey;
    }

    /**
     * get the copyleaks result url
     * @return url
     */
    public String getResultUrl() {
        return "https://copyleaks.com/compare-embed/education/"+getCopyLeaksId()+"/1?key="+getReadOnlyKey();
    }
}
