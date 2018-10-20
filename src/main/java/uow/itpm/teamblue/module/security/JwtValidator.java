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
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.User;

@Component
public class JwtValidator {

    /**
     * JWT token validation returns the user if valid or invalid token exception
     * @param token
     * @return User
     */
    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SecurityConstants.secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();
            user.setUsername(body.getSubject());
            user.setId(Integer.parseInt((String) body.get("id")));
            user.setRole((String) body.get("role"));
        }catch(Exception e){
           throw new RuntimeException("Invalid token");
        }

        return user;
    }
}
