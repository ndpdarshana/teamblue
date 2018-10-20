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
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.SubmitResponse;
import uow.itpm.teamblue.model.TextInputRequest;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.services.RequestHandlerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TextController class contains the entry point of REST API /rest level requests.
 * Authentication filter apply for this class. Therefore token must be comes with
 * request header in oder to access these methods.
 */
@RestController
@RequestMapping("/rest")
public class TextController {

    @Autowired
    private RequestHandlerService requestHandlerService;

    @Autowired
    UserRepository userRepository;

    /**
     * This method takes text inputs and parse to RequestHandlerService.
     * Request endpoint: /rest/text
     * HTTP Method: Post
     * @param request
     * @param body
     * @return SubmitResponse object with success or failure status
     */
    @PostMapping("/text")
    public SubmitResponse textIn(HttpServletRequest request,  @RequestBody TextInputRequest body){
        return requestHandlerService.textInputHandler(body, getUser(request));
    }

    /**
     * This method returns the result for given bookId
     * Request endpoint: /rest/text/{bookId}
     * HTTP Method: Get
     * @param request
     * @param bookId
     * @return SubmitResponse object contain result of the given bookId
     */
    @GetMapping("/text/{bookId}")
    public SubmitResponse getResult(HttpServletRequest request, @PathVariable("bookId") Integer bookId){
        return requestHandlerService.getResult(bookId, getUser(request));
    }

    /**
     * This method returns the all documents belongs to the user.
     * Request endpoint: /rest/documents/all
     * HTTP Method: Get
     * @param request
     * @return list of SubmitResponse objects containing book results
     */
    @GetMapping("/documents/all")
    public List<SubmitResponse> getAllDocuments(HttpServletRequest request){
        return requestHandlerService.getAllDocuments(getUser(request));
    }

    /**
     * This method is to retrive user from database using username.
     * @param request
     * @return User object
     */
    private User getUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return userRepository.findByUsername(user.getUsername());
    }

}
