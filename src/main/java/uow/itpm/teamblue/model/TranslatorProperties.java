package uow.itpm.teamblue.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "translator")
public class TranslatorProperties {
    private String apikey;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApikey() {

        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
