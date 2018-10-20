/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.AuthenticationTokenResponse;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.module.security.JwtTokenGenerator;
import uow.itpm.teamblue.services.AuthenticationService;

/**
 * AuthenticationController class contains the entry point of REST API /auth level requests.
 * Authentication filter does not apply for this class.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    /**
     * This method is used to generate Token using given user details. If authentication failed, there will not be token in the response.
     * Request endpoint: /rest/text
     * HTTP Method: Post
     * @param user
     * @return Json object containing token and status.
     */
    @PostMapping("/token")
    public @ResponseBody AuthenticationTokenResponse generate(@RequestBody final User user){
        if(user != null) {
            return authenticationService.getToken(user);
        }
        return new AuthenticationTokenResponse(HttpStatus.UNAUTHORIZED, null);
    }
}
