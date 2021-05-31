package com.elearning.platform.services;

import com.elearning.core.mappers.BatchMapper;
import com.elearning.dao.BatchDao;
import com.elearning.entities.Assignment;
import com.elearning.entities.Batch;
import com.elearning.entities.Student;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.requests.BatchRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.StudentResponse;
import com.elearning.utility.IdGenerator;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class BatchServiceImpl implements BatchService {
    private final BatchDao batchDao;
    private final BatchMapper batchMapper;

    @Inject
    public BatchServiceImpl(BatchDao batchDao, BatchMapper batchMapper) {
        this.batchDao = batchDao;
        this.batchMapper = batchMapper;
    }

    @Override
    @Transactional
    public BatchResponse getBatch(String externalId) {
        Optional<Batch> batch = batchDao.findByExternalId(externalId);
        if(!batch.isPresent()) {
            throw new ElearningException("Unable to find batch with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return batchMapper.mapEntityToResponse(batch.get());
    }

    @Override
    public List<StudentResponse> getStudentsInBatch(String batchId) {
        BatchResponse batch = getBatch(batchId);
        return batch.getStudents();
    }

    @Override
    public Long getTimeSlotOfBatch(String batchId) {
        BatchResponse batch = getBatch(batchId);
        return batch.getTimeSlotOfDay();
    }

    @Override
    @Transactional
    public BatchResponse createBatch(BatchRequest batchRequest) {
        Batch batch = new Batch();
        batchMapper.mapRequestToEntity(batch, batchRequest);
        batch.setExternalId(IdGenerator.generateBatchId());
        batchDao.create(batch);
        return batchMapper.mapEntityToResponse(batch);
    }

    @Override
    public BatchResponse updateBatchDetails(BatchRequest batchRequest) {
        Optional<Batch> batch = batchDao.findByExternalId(batchRequest.getExternalId());
        if(!batch.isPresent()) {
            throw new ElearningException("Unable to find batch with id :: " + batchRequest.getExternalId(), Response.Status.NOT_FOUND);
        }
        batchMapper.mapRequestToEntity(batch.get(), batchRequest);
        batchDao.update(batch.get());
        return batchMapper.mapEntityToResponse(batch.get());
    }

    @Override
    @Transactional
    public BatchResponse addStudentToBatch(String batchId, Student student) {
        Optional<Batch> batch = batchDao.findByExternalId(batchId);
        if(!batch.isPresent()) {
            throw new ElearningException("Unable to find batch with id :: " + batchId, Response.Status.NOT_FOUND);
        }
        batch.get().getEnrolledStudents().add(student);
        student.getEnrolledBatches().add(batch.get());
        return batchMapper.mapEntityToResponse(batch.get());
    }

    @Override
    @Transactional
    public BatchResponse addAssignmentToBatch(String batchId, Assignment assignment) {
        Optional<Batch> batch = batchDao.findByExternalId(batchId);
        if(!batch.isPresent()) {
            throw new ElearningException("Unable to find batch with id :: " + batchId, Response.Status.NOT_FOUND);
        }
        batch.get().getAssignments().add(assignment);
        assignment.setAssociatedBatch(batch.get());
        return batchMapper.mapEntityToResponse(batch.get());
    }
}
