/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied of reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.module.security;

/**
 * Constant values used in Authentication module.
 */
public class SecurityConstants {
    public static final String secret = "4mx}fY+r&:$}/u]B"; //Secret value to encode/decode tokens
    public static final String defaultFilterProcessUrl = "/rest/**"; //Rest endpoint that restricted for valid tokens
}
