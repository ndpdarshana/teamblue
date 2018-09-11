package uow.itpm.teamblue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlagiarismCheck {
    private String text;
    private String language;
    @JsonProperty("ProcessId")
    private String processId;
    private PlagiarismApiStatus plagiarismApiStatus;
    private List<PlagiarismApiResult> plagiarismApiResultList;

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
}
