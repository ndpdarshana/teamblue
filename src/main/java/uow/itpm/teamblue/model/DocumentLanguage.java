package uow.itpm.teamblue.model;

import javax.persistence.*;

@Entity
public class DocumentLanguage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id")
    private Document document;

    private String lang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @OneToOne(mappedBy = "id")
    @JoinColumn(name = "doc_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
