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
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;

/**
 * UserService class handle user level requests
 */
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * This method create a user if the email or username unique. Otherwise return an error.
     * @param user
     * @return
     */
    public User createUser(User user){
        User dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser != null && dbUser.getId() > 0){
            dbUser.setMessage("Email exists");
            return dbUser;
        }
        dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser != null && dbUser.getId() >0 ){
            dbUser.setMessage("Username exists");
            return dbUser;
        }
        dbUser = userRepository.save(user);
        return dbUser;
    }
}
