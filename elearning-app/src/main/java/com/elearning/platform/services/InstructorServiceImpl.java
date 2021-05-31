package com.elearning.platform.services;

import com.elearning.core.mappers.BatchMapper;
import com.elearning.core.mappers.InstructorMapper;
import com.elearning.dao.InstructorDao;
import com.elearning.entities.Instructor;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.requests.InstructorRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.InstructorResponse;
import com.elearning.utility.IdGenerator;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstructorServiceImpl implements InstructorService {
    private final InstructorDao instructorDao;
    private final InstructorMapper instructorMapper;
    private final BatchMapper batchMapper;

    @Inject
    public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper, BatchMapper batchMapper) {
        this.instructorDao = instructorDao;
        this.instructorMapper = instructorMapper;
        this.batchMapper = batchMapper;
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

    @Override
    @Transactional
    public InstructorResponse updateInstructorDetails(InstructorRequest instructorRequest) {
        Optional<Instructor> instructor = instructorDao.findByExternalId(instructorRequest.getExternalId());
        if(!instructor.isPresent()) {
            throw new ElearningException("Unable to find instructor with id :: " + instructorRequest.getExternalId(), Response.Status.NOT_FOUND);
        }
        instructorMapper.mapRequestToEntity(instructor.get(), instructorRequest);
        instructorDao.update(instructor.get());
        return instructorMapper.mapEntityToResponse(instructor.get());
    }

    @Override
    @Transactional
    public InstructorResponse addInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructorMapper.mapRequestToEntity(instructor, instructorRequest);
        instructor.setExternalId(IdGenerator.generateInstructorId());
        instructorDao.create(instructor);
        return instructorMapper.mapEntityToResponse(instructor);
    }

    @Override
    public List<BatchResponse> getAllocatedBatches(String instructorId) {
        Optional<Instructor> instructor = instructorDao.findByExternalId(instructorId);
        if(!instructor.isPresent()) {
            throw new ElearningException("Unable to find instructor with id :: " + instructorId, Response.Status.NOT_FOUND);
        }
        return instructor.get().getAllocatedBatches().stream()
                .map(batchMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
