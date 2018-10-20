/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import javax.persistence.*;
import java.util.List;

/**
 * Database entity model for Document
 * This class creates the database table Document if not available and used to do all the transactions with database
 */
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id; //MySQL Auto generated Id field

    @OneToOne
    @JoinColumn(name = "user_id", columnDefinition = "integer")
    private User user; //Associated user with the document
    private String documentName; //Document name
    @Column(columnDefinition = "blob" )
    private String text; //Document content as a blob

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id", columnDefinition = "integer")
    @ElementCollection
    private List<DocumentLanguage> languagesList; //Associated Document languages Currently only contains 2 objects

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id", columnDefinition = "integer")
    @ElementCollection
    private List<CopyLeaksMetadata> copyLeaksMetadataList; //Associated copyleaks metadata details

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_id", columnDefinition = "integer")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToMany(mappedBy = "copyLeaksMetadata", cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id", columnDefinition = "integer")
    public List<CopyLeaksMetadata> getCopyLeaksMetadataList() {
        return copyLeaksMetadataList;
    }

    public void setCopyLeaksMetadataList(List<CopyLeaksMetadata> copyLeaksMetadataList) {
        this.copyLeaksMetadataList = copyLeaksMetadataList;
    }

    public List<DocumentLanguage> getLanguagesList() {
        return languagesList;
    }

    @OneToMany(mappedBy = "documentLanguage", cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id", columnDefinition = "integer")
    public void setLanguagesList(List<DocumentLanguage> languagesList) {
        this.languagesList = languagesList;
    }
}
