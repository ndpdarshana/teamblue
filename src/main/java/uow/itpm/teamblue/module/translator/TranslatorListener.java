/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

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
