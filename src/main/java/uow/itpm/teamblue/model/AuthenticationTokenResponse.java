/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import org.springframework.http.HttpStatus;

public class AuthenticationTokenResponse {
    private HttpStatus status; //Status of the token generation: success or failed
    private String token; //Associate token generated via JWT service

    public AuthenticationTokenResponse(HttpStatus status, String token){
        this.status = status;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HttpStatus getStatus() {

        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
