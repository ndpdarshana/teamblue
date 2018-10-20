/*
 * Copyright (c) 2018 TeamBlue - All rights Reserved.
 *
 * This file is a part of class project in CSCI814 - Teamblue, UOW.
 *
 * This code can not be copied or reuse until CSCI814 2018 Spring session grading release date of 29 November 2018.
 * Written by Prabhath Darshana <pdnd723@uowmail.edu.au>
 */

package uow.itpm.teamblue.model.repo;

import org.springframework.data.repository.CrudRepository;
import uow.itpm.teamblue.model.CopyLeaksMetadata;

/**
 * CopyLeaksMetadataRepository used by spring jpa to deal with CopyLeaksMetadata table
 */
public interface CopyLeaksMetadataRepository extends CrudRepository<CopyLeaksMetadata, Long> {
}
