package com.elearning.platform.services;

import com.elearning.core.mappers.InstructorMapper;
import com.elearning.dao.InstructorDao;
import com.elearning.entities.Instructor;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.responses.InstructorResponse;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class InstructorServiceImpl implements InstructorService {
    private final InstructorDao instructorDao;
    private final InstructorMapper instructorMapper;

    @Inject
    public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper) {
        this.instructorDao = instructorDao;
        this.instructorMapper = instructorMapper;
    }

    @Override
    @Transactional
    public InstructorResponse getInstructor(String externalId) {
        Optional<Instructor> instructor = instructorDao.findByExternalId(externalId);
        if(!instructor.isPresent()) {
            throw new ElearningException("Unable to find instructor with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return instructorMapper.mapEntityToResponse(instructor.get());
    }
}
