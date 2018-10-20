/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.module.security.JwtValidator;
import uow.itpm.teamblue.services.UserService;

/**
 * UserController class contains the entry point of REST API /user level requests.
 * Authentication filter doesn't apply for this class.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtValidator jwtValidator;

    /**
     * This method used to create new user in the database.
     * Request endpoint: /user/signup
     * HTTP Method: Post
     * @param user object
     * @return status: success or failed as a json response
     */
    @PostMapping("/signup")
    public @ResponseBody String addNewUser(@RequestBody User user){
        if(user.getEmail() == null || user.getEmail().equals("") || user.getUsername() == null
                || user.getUsername().equals("")){
            return "{'status':'failed'}";
        }
        userService.createUser(user);
        if(user.getId() != null && user.getId()>0) {
            return "{'status':'success'}";
        }
        return "{'status':'failed'}";
    }

    /**
     * This method used to return all users details. (Not used in front end)
     * Request endpoint: /user/all
     * HTTP Method: Get
     * @return Json user object array
     */
//    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * This method returns the user associated with valid jwt token
     * Request endpoint: /user/{token}
     * HTTP Method: Get
     * @param token
     * @return status: success or failed as a json response
     */
    @GetMapping("/{token}")
    public @ResponseBody User getUser(@PathVariable("token") String token){
        User user = jwtValidator.validate(token);
        user = userRepository.findByUsername(user.getUsername());
        return user;
    }

}
