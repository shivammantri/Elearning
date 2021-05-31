package com.elearning.platform.services;

import com.elearning.core.mappers.AssignmentMapper;
import com.elearning.dao.AssignmentDao;
import com.elearning.entities.Assignment;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.responses.AssignmentResponse;
import com.elearning.model.responses.BatchResponse;
import com.elearning.utility.IdGenerator;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Optional;

public class AssignmentServiceImpl implements AssignmentService{
    private final AssignmentDao assignmentDao;
    private final BatchService batchService;
    private final AssignmentMapper assignmentMapper;

    @Inject
    public AssignmentServiceImpl(AssignmentDao assignmentDao, BatchService batchService, AssignmentMapper assignmentMapper) {
        this.assignmentDao = assignmentDao;
        this.batchService = batchService;
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public AssignmentResponse uploadAssignment(Assignment assignment) {
        assignment.setExternalId(IdGenerator.generateAssignmentId());
        assignmentDao.create(assignment);
        return assignmentMapper.mapEntityToResponse(assignment);
    }

    @Override
    public void downloadAssignment(String assignmentId, String pathToWrite) throws IOException {
        Optional<Assignment> assignment = assignmentDao.getAssignment(assignmentId);
        if(!assignment.isPresent()) {
            throw new ElearningException("No assignment found with id :: " + assignmentId, Response.Status.NOT_FOUND);
        }
        String name = assignment.get().getFileName();
        pathToWrite = pathToWrite + "/" + name;
        File file = new File(pathToWrite);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(assignment.get().getFileData());
        outputStream.close();
    }

    @Override
    public BatchResponse assignToBatch(String assignmentId, String batchId) {
        Optional<Assignment> assignment = assignmentDao.getAssignment(assignmentId);
        if(!assignment.isPresent()) {
            throw new ElearningException("No assignment found with id :: " + assignmentId, Response.Status.NOT_FOUND);
        }
        return batchService.addAssignmentToBatch(batchId, assignment.get());
    }
}
