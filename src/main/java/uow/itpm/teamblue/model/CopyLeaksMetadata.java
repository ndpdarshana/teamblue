package uow.itpm.teamblue.model;

import javax.persistence.*;

@Entity
public class CopyLeaksMetadata {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "doc_id")
    private Document document;

    private String copyLeaksId;

    private String status;

    @Column(length = 1000)
    private String result;

    private String lang;

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
}
