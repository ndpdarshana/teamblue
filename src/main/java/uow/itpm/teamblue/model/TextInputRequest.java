package uow.itpm.teamblue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TextInputRequest {
    private String text;
    @JsonProperty("lang")
    private List<String> languageList;
    private boolean isFile;
    private String fileName;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<String> languageList) {
        this.languageList = languageList;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
