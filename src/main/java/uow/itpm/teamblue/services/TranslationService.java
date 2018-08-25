package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.model.TranslatorProperties;
import uow.itpm.teamblue.model.TranslatorResponse;
import uow.itpm.teamblue.module.translator.TranslatorApi;

@Service
public class TranslationService {
    @Autowired
    private TranslatorApi translatorApi;
    @Autowired
    private TranslatorProperties translatorProperties;

    public void detectLanguage(Translate translate){
        translatorApi.getLanguage(translate);
    }

    public void translate(Translate translate){
        TranslatorResponse response = translatorApi.getTranslated(translate);
        System.out.println(response.getLang());
        System.out.println(response.getCode());
        System.out.println(response.getText());
    }
}
