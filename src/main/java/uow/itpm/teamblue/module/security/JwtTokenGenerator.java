/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.module.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.AuthenticationTokenResponse;
import uow.itpm.teamblue.model.User;

/**
 * JwtTokenGenerator Class used to generate the token for authenticated users
 */
@Component
public class JwtTokenGenerator {

    /**
     * This method will generate the token with preset configurations. For the moment, token expiration has disabled.
     * To set token expiration uncomment the line 38
     * @param user
     * @return
     */
    public AuthenticationTokenResponse generate(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        String token = Jwts.builder()
//                .setExpiration(new Date(100)) //Uncomment thi line for enable exipiration time for token
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.secret)
                .compact();
        AuthenticationTokenResponse tokenResponse = new AuthenticationTokenResponse(HttpStatus.CREATED, token);

        return tokenResponse;

    }
}
