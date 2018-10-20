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

/**
 * JsonResponse for copyleaks response
 */
public class PlagiarismApiResult {
    @JsonProperty("URL")
    private String url;
    @JsonProperty("Percents")
    private Integer Percents;
    @JsonProperty("NumberOfCopiedWords")
    private Integer NumberOfCopiedWords;
    @JsonProperty("ComparisonReport")
    private String comparisonReport;
    @JsonProperty("CachedVersion")
    private String cachedVersion;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Introduction")
    private String introduction;
    @JsonProperty("EmbededComparison")
    private String embededComparison;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPercents() {
        return Percents;
    }

    public void setPercents(Integer percents) {
        Percents = percents;
    }

    public Integer getNumberOfCopiedWords() {
        return NumberOfCopiedWords;
    }

    public void setNumberOfCopiedWords(Integer numberOfCopiedWords) {
        NumberOfCopiedWords = numberOfCopiedWords;
    }

    public String getComparisonReport() {
        return comparisonReport;
    }

    public void setComparisonReport(String comparisonReport) {
        this.comparisonReport = comparisonReport;
    }

    public String getCachedVersion() {
        return cachedVersion;
    }

    public void setCachedVersion(String cachedVersion) {
        this.cachedVersion = cachedVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEmbededComparison() {
        return embededComparison;
    }

    public void setEmbededComparison(String embededComparison) {
        this.embededComparison = embededComparison;
    }
}
