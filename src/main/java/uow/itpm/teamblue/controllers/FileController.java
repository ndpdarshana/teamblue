/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uow.itpm.teamblue.model.SubmitResponse;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.services.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileController class contains the entry point of REST API /rest level requests.
 * Authentication filter apply for this class. Therefore token must be comes with
 * request header in oder to access these methods.
 */
@RestController
@RequestMapping("/rest")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UserRepository userRepository;

    /**
     * This method is used to handle upload file requests.
     * Request endpoint: /rest/uploadFile
     * HTTP Method: Post
     * @param request
     * @param file
     * @param language
     * @return SubmitResponse object with status of success or failure.
     */
    @PostMapping("/uploadFile")
    public SubmitResponse uploadFile(HttpServletRequest request, @RequestParam("file")MultipartFile file,
                                         @RequestParam("language")String language){
        User user = (User) request.getSession().getAttribute("user");
        user = userRepository.findByUsername(user.getUsername());

        SubmitResponse submitResponse = fileStorageService.storeFile(file, user, language);

        return submitResponse;
    }

    /**
     * This method is used to handle upload multiple files requests.
     * Request endpoint: /rest/uploadMultipleFiles
     * HTTP Method: Post
     * @param request
     * @param files
     * @param language
     * @return List of SubmitResponses containing results for each file
     * This method is not used in UI implementation
     */
    @PostMapping("/uploadMultipleFiles")
    public List<SubmitResponse> uploadMultipleFiles(HttpServletRequest request,
                                                        @RequestParam("files") MultipartFile[] files,
                                                        @RequestParam("language")String language){
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(request, file, language))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to download files.
     * Request endpoint: /rest/downloadFile/{fileName:.+}
     * HTTP Method: Get
     * @param fileName
     * @param request
     * @return Return the binary file
     * This method is not used in UI implementation
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        logger.debug("Get file " + fileName);
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
