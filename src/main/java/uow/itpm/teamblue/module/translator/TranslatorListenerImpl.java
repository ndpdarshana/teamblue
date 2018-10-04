package uow.itpm.teamblue.module.translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.model.repo.CopyLeaksMetadataRepository;
import uow.itpm.teamblue.services.PlagiarismCheckerService;
import uow.itpm.teamblue.services.TranslationService;

public class TranslatorListenerImpl implements TranslatorListener{

    private static final Logger logger = LoggerFactory.getLogger(TranslatorListenerImpl.class);

    @Override
    public void detectLanguageResponseHandler(TranslatorResponse translatorResponse, Document document,
                                              Translate translate,
                                              TranslationService translationService,
                                              PlagiarismCheckerService plagiarismCheckerService,
                                              CopyLeaksMetadataRepository copyLeaksMetadataRepository) {
        logger.debug("Response received");
        logger.debug(translatorResponse.getLang());

        translate.setFromLanguage(translatorResponse.getLang());
        //TODO Currently support only for one additional language. In future it can support multiple languages.
        //This code snippet need to change.
        translatorResponse = translationService.translate(translate);

        submitPlagiarismCheckJob(document.getText(), translate.getFromLanguage(), document,
                plagiarismCheckerService, copyLeaksMetadataRepository);
        submitPlagiarismCheckJob(translatorResponse.getText().get(0), translate.getToLanguage(), document,
                plagiarismCheckerService, copyLeaksMetadataRepository);
    }

    private void submitPlagiarismCheckJob(String text, String language, Document document,
                                          PlagiarismCheckerService plagiarismCheckerService,
                                          CopyLeaksMetadataRepository copyLeaksMetadataRepository){
        PlagiarismCheck plagiarismCheck = new PlagiarismCheck();
        plagiarismCheck.setText(text);
        plagiarismCheck.setLanguage(language);
        plagiarismCheck = plagiarismCheckerService.checkText(plagiarismCheck);

        CopyLeaksMetadata copyLeaksMetadata = new CopyLeaksMetadata();
        copyLeaksMetadata.setDocument(document);
        copyLeaksMetadata.setStatus("submitted");
        copyLeaksMetadata.setCopyLeaksId(plagiarismCheck.getProcessId());
        plagiarismCheck = plagiarismCheckerService.getKey(plagiarismCheck);
        copyLeaksMetadata.setReadOnlyKey(plagiarismCheck.getKey());
        copyLeaksMetadata.setLang(language);
        copyLeaksMetadataRepository.save(copyLeaksMetadata);
    }
}

