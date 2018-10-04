package uow.itpm.teamblue.model;

import java.util.List;

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
