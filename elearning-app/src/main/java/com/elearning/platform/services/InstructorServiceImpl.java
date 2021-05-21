package com.elearning.platform.services;

import com.elearning.dao.InstructorDao;
import com.elearning.entities.Instructor;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class InstructorServiceImpl implements InstructorService {
    private final InstructorDao instructorDao;

    @Inject
    public InstructorServiceImpl(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @Override
    @Transactional
    public Instructor getInstructor(String externalId) {
        Optional<Instructor> instructor = instructorDao.findByExternalId(externalId);
        if(!instructor.isPresent()) {
            throw new ElearningException("Unable to find data with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return instructor.get();
    }
}
