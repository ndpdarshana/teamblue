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

        PlagiarismCheck plagiarismCheck = new PlagiarismCheck();
        plagiarismCheck.setText(translatorResponse.getText().get(0));
        plagiarismCheck.setLanguage(translate.getFromLanguage());
        plagiarismCheck = plagiarismCheckerService.checkText(plagiarismCheck);

        CopyLeaksMetadata copyLeaksMetadata = new CopyLeaksMetadata();
        copyLeaksMetadata.setDocument(document);
        copyLeaksMetadata.setStatus("submitted");
        copyLeaksMetadata.setCopyLeaksId(plagiarismCheck.getProcessId());
        copyLeaksMetadataRepository.save(copyLeaksMetadata);
    }
}

