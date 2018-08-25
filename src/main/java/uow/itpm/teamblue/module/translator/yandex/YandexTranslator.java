package uow.itpm.teamblue.module.translator.yandex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.model.TranslatorProperties;
import uow.itpm.teamblue.model.TranslatorResponse;
import uow.itpm.teamblue.module.translator.TranslatorApi;

import java.util.Arrays;

public class YandexTranslator implements TranslatorApi {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TranslatorProperties translatorProperties;

    @Override
    public TranslatorResponse getLanguage(Translate translate) {
        String url = translatorProperties.getUrl() + "/detect?hint=en,de&key=" + translatorProperties.getApikey();
        return getResponse(url, translate);
    }

    @Override
    public TranslatorResponse getTranslated(Translate translate) {
        String url = translatorProperties.getUrl() + "/translate?lang=" + translate.getFromLanguage()
                + "-" + translate.getToLanguage() + "&key="
                + translatorProperties.getApikey();
//        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-si&key=trnsl.1.1.20180825T111028Z.90b38aa17941b979.5d7446dd6e68768bd64e2107bb1dbda242031900";
        return getResponse(url, translate);
    }

    private TranslatorResponse getResponse(String url, Translate translate){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentLength(translate.getText().length());
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        String body = "text=" + translate.getText();
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url, request, TranslatorResponse.class);
    }
}
