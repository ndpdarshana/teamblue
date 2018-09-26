package uow.itpm.teamblue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
