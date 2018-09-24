package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.model.repo.DocumentRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestHandlerService {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private PlagiarismCheckerService plagiarismCheckerService;
    @Autowired
    private DocumentRepository documentRepository;


    public SubmitResponse textInputHandler(TextInputRequest textInputRequest, User user){
        Document document = new Document();
        document.setDocumentName("doc_" + user.getUsername());
        document.setText(textInputRequest.getText());
        document.setUser(user);
//        Set<DocumentLanguage> documentLanguages = new HashSet<>();
        List<DocumentLanguage> documentLanguages = new ArrayList<>();
        String languages = "";
        String tempLang = "";
        for(String lang:textInputRequest.getLanguageList()){
            DocumentLanguage documentLanguage = new DocumentLanguage();
            documentLanguage.setLang(lang);
            documentLanguages.add(documentLanguage);
        }
        document.setLanguagesList(documentLanguages);
        document = documentRepository.save(document);

        Translate translate = new Translate();
        translate.setText(document.getText());
        TranslatorResponse translatorResponse = translationService.detectLanguage(translate);
        translate.setFromLanguage(translatorResponse.getLang());
        //TODO Currently support only for one additional language. In future it can support multiple languages. This code snipen need to change.
        DocumentLanguage docLang = documentLanguages.get(0);
        translate.setToLanguage(docLang.getLang());
        translatorResponse = translationService.translate(translate);



        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setStatus("accepted");
        submitResponse.setMessage("Your document is being processed");
        return submitResponse;
    }
}
