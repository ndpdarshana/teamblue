package uow.itpm.teamblue.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", columnDefinition = "integer")
    private User user;
    private String documentName;
    @Column(columnDefinition = "blob" )
    private String text;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id", columnDefinition = "integer")
    @ElementCollection
    private List<DocumentLanguage> languagesList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id", columnDefinition = "integer")
    @ElementCollection
    private List<CopyLeaksMetadata> copyLeaksMetadataList;

    private String languages;

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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
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
