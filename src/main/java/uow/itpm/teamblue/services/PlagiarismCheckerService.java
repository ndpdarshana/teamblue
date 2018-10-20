/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uow.itpm.teamblue.model.PlagiarismApiUser;
import uow.itpm.teamblue.model.PlagiarismCheck;
import uow.itpm.teamblue.module.plagiarismapi.PlagiarismCheckerApi;

/**
 * PlagiarismCheckerService class provide the logical functionality of copyleaks implementation
 */
@Service
public class PlagiarismCheckerService {
    @Autowired
    private PlagiarismCheckerApi plagiarismCheckerApi;

    public void loginToApi(){
        PlagiarismApiUser plagiarismApiUser = plagiarismCheckerApi.loginToApi();
        System.out.println(plagiarismApiUser.getMessage());
        System.out.println(plagiarismApiUser.getAccessToken());
    }

    /**
     * This method submit the request to plagiarism api implementation.
     * @param plagiarismCheck
     * @return PlagiarismCheck
     */
    public PlagiarismCheck checkText(PlagiarismCheck plagiarismCheck){
        return plagiarismCheckerApi.checkByText(plagiarismCheck);
    }

    /**
     * This method route the check result requests to api caller status check implementation
     * @param plagiarismCheck
     * @return PlagiarismCheck
     */
    public PlagiarismCheck checkStatus(PlagiarismCheck plagiarismCheck){
        return plagiarismCheckerApi.status(plagiarismCheck);
    }

    /**
     * This method recursively check until plagiarism check reach 100% or not. if reached then send call to get result
     * @param plagiarismCheck
     * @return
     */
    public PlagiarismCheck result(PlagiarismCheck plagiarismCheck){
        if(plagiarismCheck.getPlagiarismApiStatus() != null
                && plagiarismCheck.getPlagiarismApiStatus().getProgressPercents() <  100){
            plagiarismCheck = checkStatus(plagiarismCheck);
            plagiarismCheck = result(plagiarismCheck);
        }
        return plagiarismCheckerApi.result(plagiarismCheck);
    }

    /**
     * This method used to retrieve the readonly key.
     * @param plagiarismCheck
     * @return
     */
    public PlagiarismCheck getKey(PlagiarismCheck plagiarismCheck){
        return plagiarismCheckerApi.generateReadOnlyKey(plagiarismCheck);
    }
}
