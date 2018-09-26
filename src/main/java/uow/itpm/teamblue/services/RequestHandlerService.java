package uow.itpm.teamblue.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.model.repo.CopyLeaksMetadataRepository;
import uow.itpm.teamblue.model.repo.DocumentRepository;
import uow.itpm.teamblue.module.plagiarismapi.PlagiarismCheckerListner;
import uow.itpm.teamblue.module.plagiarismapi.PlagiarismCheckerListnerImpl;
import uow.itpm.teamblue.module.translator.TranslatorListener;
import uow.itpm.teamblue.module.translator.TranslatorListenerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public SubmitResponse textInputHandler(TextInputRequest textInputRequest, User user){
        Document document = new Document();

        if(textInputRequest.isFile()){
            document.setDocumentName(textInputRequest.getFileName());
        }else {
            document.setDocumentName("doc_" + user.getUsername());
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

        PlagiarismCheckerListner plagiarismCheckerListner = new PlagiarismCheckerListnerImpl();
        submitPlagiarismCheckJob(document);

        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setDocId(document.getId());
        submitResponse.setDocument(document);
        submitResponse.setStatus("accepted");
        submitResponse.setMessage("Your document is being processed");
        return submitResponse;
    }

    public SubmitResponse getResult(Integer bookId, User user){
        Document document = documentRepository.findById(bookId).get();
        List<PlagiarismCheck> plagiarismCheckList = new ArrayList<>();
        for(CopyLeaksMetadata copyLeaksMetadata: document.getCopyLeaksMetadataList()){
            if(copyLeaksMetadata.getStatus() != null && copyLeaksMetadata.getStatus().equals("submitted")){
                PlagiarismCheck plagiarismCheck = new PlagiarismCheck();
                plagiarismCheck.setProcessId(copyLeaksMetadata.getCopyLeaksId());
                plagiarismCheck = plagiarismCheckerService.checkStatus(plagiarismCheck);
                if(plagiarismCheck.getPlagiarismApiStatus().getStatus().equals("Finished")){
                    copyLeaksMetadata.setStatus(plagiarismCheck.getPlagiarismApiStatus().getStatus());
                    copyLeaksMetadataRepository.save(copyLeaksMetadata);
                    plagiarismCheck = plagiarismCheckerService.result(plagiarismCheck);
                }
                plagiarismCheckList.add(plagiarismCheck);
            }else if(copyLeaksMetadata.getStatus().equals("Finished")){
                PlagiarismCheck plagiarismCheck = new PlagiarismCheck();
                plagiarismCheck.setProcessId(copyLeaksMetadata.getCopyLeaksId());
                plagiarismCheck = plagiarismCheckerService.result(plagiarismCheck);
                plagiarismCheckList.add(plagiarismCheck);
            }
        }
        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setStatus("Result");
        submitResponse.setDocId(document.getId());
//        submitResponse.setDocument(document);
        submitResponse.setPlagiarismCheck(plagiarismCheckList);
        return submitResponse;
    }

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

    private void submitPlagiarismCheckJob(Document document){
        logger.debug("Submit plagiarism check job");
        new Thread(()->{
            logger.debug("Plagiarism check job thread initiated");

            PlagiarismCheck plagiarismCheck = new PlagiarismCheck();
            plagiarismCheck.setText(document.getText());
            plagiarismCheck = plagiarismCheckerService.checkText(plagiarismCheck);

            CopyLeaksMetadata copyLeaksMetadata = new CopyLeaksMetadata();
            copyLeaksMetadata.setDocument(document);
            copyLeaksMetadata.setStatus("submitted");
            copyLeaksMetadata.setLang(plagiarismCheck.getLanguage());
            copyLeaksMetadata.setCopyLeaksId(plagiarismCheck.getProcessId());
            copyLeaksMetadataRepository.save(copyLeaksMetadata);
        }).start();
    }
}
