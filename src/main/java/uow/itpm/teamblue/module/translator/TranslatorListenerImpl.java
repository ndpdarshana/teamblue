/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.module.translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.model.repo.CopyLeaksMetadataRepository;
import uow.itpm.teamblue.services.PlagiarismCheckerService;
import uow.itpm.teamblue.services.TranslationService;

/**
 * TranslatorListenerImpl class is will execute when got the result from translation api.
 */
public class TranslatorListenerImpl implements TranslatorListener{

    private static final Logger logger = LoggerFactory.getLogger(TranslatorListenerImpl.class);

    /**
     * detectLanguageResponseHandler method will call when result got from the translator api for detect the language in original text.
     * Then submit the both requests for copyleaks
     * 1. Original text plagiarism check request
     * 2. Selected second language translation submission
     * 3. Submit the translated text to plagiarism check
     * @param translatorResponse
     * @param document
     * @param translate
     * @param translationService
     * @param plagiarismCheckerService
     * @param copyLeaksMetadataRepository
     */
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

    /**
     * This method submit the copyleaks requests and save the data into database
     * @param text
     * @param language
     * @param document
     * @param plagiarismCheckerService
     * @param copyLeaksMetadataRepository
     */
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

