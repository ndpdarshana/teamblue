package uow.itpm.teamblue.model;

public class PlagiarismApiStatus {
    private String status;
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
