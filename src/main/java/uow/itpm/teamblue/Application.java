/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import uow.itpm.teamblue.model.FileStorageProperties;
import uow.itpm.teamblue.model.PlagiarismApiProperties;
import uow.itpm.teamblue.model.TranslatorProperties;
import uow.itpm.teamblue.module.plagiarismapi.PlagiarismCheckerApi;
import uow.itpm.teamblue.module.plagiarismapi.copyleaks.CopyLeaksApi;
import uow.itpm.teamblue.module.translator.TranslatorApi;
import uow.itpm.teamblue.module.translator.yandex.YandexTranslator;

/**
 * Application.java is the main class of this project. Spring boot container get launched
 * within this class with spring autowire configurations.
 */

@SpringBootApplication
/**
 * @EnableConfigurationProperties Annotation load the custom property file objects into the spring container.
 */
@EnableConfigurationProperties({
        FileStorageProperties.class,
        TranslatorProperties.class,
        PlagiarismApiProperties.class
})
public class Application {
    /**
     * Main method of the program. This require no parameters. When program executes, it'll automatically
     * launched the spring boot container
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    /**
     * Spring bean generators load components classes into the spring container
     *
     */

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public TranslatorApi getTranslatorApi(){
        return new YandexTranslator();
    }

    @Bean
    public PlagiarismCheckerApi getPlagiarismCheckerApi(){
        return new CopyLeaksApi();
    }
}
