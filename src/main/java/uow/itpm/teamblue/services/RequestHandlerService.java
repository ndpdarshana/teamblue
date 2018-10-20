/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.model.repo.CopyLeaksMetadataRepository;
import uow.itpm.teamblue.model.repo.DocumentRepository;
import uow.itpm.teamblue.module.translator.TranslatorListener;
import uow.itpm.teamblue.module.translator.TranslatorListenerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * RequestHandlerService will handle all the requests from UI to submit text jobs
 */
@Service
public class RequestHandlerService {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private PlagiarismCheckerService plagiarismCheckerService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private CopyLeaksMetadataRepository copyLeaksMetadataRepository;

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerService.class);

    /**
     * This method will handle the text input requests and save requests to the database.
     * @param textInputRequest
     * @param user
     * @return
     */
    public SubmitResponse textInputHandler(TextInputRequest textInputRequest, User user){
        Document document = new Document();

        if(textInputRequest.isFile()){
            document.setDocumentName(textInputRequest.getFileName());
        }else {
            String str = textInputRequest.getText().substring(0, 5);
            document.setDocumentName("doc_" + str);
        }
        document.setText(textInputRequest.getText());
        document.setUser(user);
        List<DocumentLanguage> documentLanguages = new ArrayList<>();
        for(String lang:textInputRequest.getLanguageList()){
            DocumentLanguage documentLanguage = new DocumentLanguage();
            documentLanguage.setLang(lang);
            documentLanguages.add(documentLanguage);
        }
        document.setLanguagesList(documentLanguages);
        document = documentRepository.save(document);


        TranslatorListener translatorListener = new TranslatorListenerImpl();
        detectLanguageRequestHandler(document, translatorListener);

//        submitPlagiarismCheckJob(document);

        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setDocId(document.getId());
        submitResponse.setDocName(document.getDocumentName());
        submitResponse.setStatus("accepted");
        submitResponse.setMessage("Your document is being processed");
        return submitResponse;
    }

    /**
     * This method will retrieve the result from api and set it to submit response object
     * @param bookId
     * @param user
     * @return
     */
    public SubmitResponse getResult(Integer bookId, User user){
        Document document = documentRepository.findById(bookId).get();
        List<PlagiarismResult> plagiarismResultList = new ArrayList<>();
        for(CopyLeaksMetadata copyLeaksMetadata: document.getCopyLeaksMetadataList()){
            PlagiarismResult plagiarismResult = new PlagiarismResult();
            plagiarismResult.setLang(copyLeaksMetadata.getLang());
            plagiarismResult.setUrl(copyLeaksMetadata.getResultUrl());
            plagiarismResultList.add(plagiarismResult);
        }
        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setStatus("Result");
        submitResponse.setDocId(document.getId());
        submitResponse.setDocName(document.getDocumentName());
        submitResponse.setPlagiarismCheck(plagiarismResultList);
        return submitResponse;
    }

    /**
     * This method get all the documents from the database for given user
     * @param user
     * @return
     */
    public List<SubmitResponse> getAllDocuments(User user){
        List<SubmitResponse> submitResponses = new ArrayList<>();
        List<Document> documents = new ArrayList<>();
        documents = documentRepository.findByUserId(user.getId());
        for(Document document:documents){
            SubmitResponse submitResponse = getResult(document.getId(), user);
            submitResponses.add(submitResponse);
        }
        return submitResponses;
    }

    /**
     * This method iniitiate the Thread to process the input text job.
     * 1st it'll call to detect the language of original text, then the response will submit to TranslatorListener
     * for further process.
     * @param document
     * @param translatorListener
     */
    private void detectLanguageRequestHandler(Document document, TranslatorListener translatorListener){
        logger.info("Detect language request handler");
        new Thread(() -> {
            logger.debug("Detect Language request initiated");

            Translate translate = new Translate();
            translate.setText(document.getText());
            TranslatorResponse translatorResponse = translationService.detectLanguage(translate);
            DocumentLanguage docLang = document.getLanguagesList().get(0);
            translate.setToLanguage(docLang.getLang());
            translatorListener.detectLanguageResponseHandler(translatorResponse, document, translate, translationService, plagiarismCheckerService, copyLeaksMetadataRepository);
        }).start();
    }
}
