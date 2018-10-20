/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.AuthenticationTokenResponse;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.module.security.JwtTokenGenerator;

/**
 * AuthenticationService class handle the authenticating process and call to security module.
 */
@Component
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    public boolean isAuthenticated(User user, User dbUser){


        if(dbUser != null && dbUser.isAuthenticated(user)){
            return true;
        }
        return false;
    }

    /**
     * This method check whether user login information authenticated or not. If user authenticated then generate the
     * creates a jwt authentication.
     * @param user
     * @return
     */
    public AuthenticationTokenResponse getToken(User user){
        User dbUser = null;
        if(user.getUsername() != null) {
            dbUser = userRepository.findByUsername(user.getUsername());
        }else if(user.getEmail() != null){
            dbUser = userRepository.findByEmail(user.getEmail());
        }
        if(dbUser != null && dbUser.isAuthenticated(user)) {
            return jwtTokenGenerator.generate(dbUser);
        }
        return new AuthenticationTokenResponse(HttpStatus.UNAUTHORIZED, null);
    }
}
