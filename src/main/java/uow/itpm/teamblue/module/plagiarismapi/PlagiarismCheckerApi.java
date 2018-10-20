/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.module.plagiarismapi;

import uow.itpm.teamblue.model.PlagiarismApiUser;
import uow.itpm.teamblue.model.PlagiarismCheck;

public interface PlagiarismCheckerApi {
    public PlagiarismApiUser loginToApi();
    public PlagiarismCheck checkByText(PlagiarismCheck plagiarismCheck);
    public PlagiarismCheck status(PlagiarismCheck plagiarismCheck);
    public PlagiarismCheck result(PlagiarismCheck plagiarismCheck);
    public PlagiarismCheck generateReadOnlyKey(PlagiarismCheck plagiarismCheck);
}
