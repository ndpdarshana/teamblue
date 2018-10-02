package uow.itpm.teamblue.model;

import java.util.List;

public class SubmitResponse {
    private Integer docId;
    private String status;
    private String message;
    private Document document;
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<PlagiarismResult> getPlagiarismCheck() {
        return plagiarismCheck;
    }

    public void setPlagiarismCheck(List<PlagiarismResult> plagiarismCheck) {
        this.plagiarismCheck = plagiarismCheck;
    }
}
