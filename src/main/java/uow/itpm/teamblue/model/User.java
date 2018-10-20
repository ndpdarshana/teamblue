/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model;

import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Database entity model for User table
 * This class creates the database table User if not available and used to do all the transactions with database
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;//MySQL Auto generated Id field
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private static String algorithm = "MD5";
    private boolean enabled = true;
    @Transient
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAuthenticated(User user){
        String hashedPw = getHashed(user.password);
        if(this.password.equals(user.password)){
            return true;
        }
        return false;
    }

    public void setPassword(String password) {
        this.password = getHashed(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Create MD5 hashing for password
     * @param text
     * @return
     */
    private String getHashed(String text){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] messageBytes = messageDigest.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageBytes);
            String hashtext = number.toString(16);
            while(hashtext.length() < 32){
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Return user json
     * @return
     */
    @Override
    public String toString(){
        return "{" +
                    "'id':"+this.id+"," +
                    "'username':'"+this.username+"'," +
                    "'email':'"+this.email+"'," +
                    "'firstName':'"+this.firstName+"'," +
                    "'lastName':'"+this.lastName+"'," +
                "}";
    }
}
