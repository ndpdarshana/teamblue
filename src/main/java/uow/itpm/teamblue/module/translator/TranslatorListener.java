package uow.itpm.teamblue.module.translator;

import uow.itpm.teamblue.model.Document;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.model.TranslatorResponse;
import uow.itpm.teamblue.model.repo.CopyLeaksMetadataRepository;
import uow.itpm.teamblue.services.PlagiarismCheckerService;
import uow.itpm.teamblue.services.TranslationService;

public interface TranslatorListener {
    public void detectLanguageResponseHandler(TranslatorResponse translatorResponse, Document document,
                                              Translate translate,
                                              TranslationService translationService,
                                              PlagiarismCheckerService plagiarismCheckerService,
                                              CopyLeaksMetadataRepository copyLeaksMetadataRepository);
}
