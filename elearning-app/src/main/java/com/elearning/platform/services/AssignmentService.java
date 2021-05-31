package com.elearning.platform.services;

import com.elearning.entities.Assignment;
import com.elearning.model.responses.AssignmentResponse;
import com.elearning.model.responses.BatchResponse;

import java.io.IOException;

public interface AssignmentService {
    public AssignmentResponse uploadAssignment(Assignment assignment);
    public void downloadAssignment(String assignmentId, String pathToWrite) throws IOException;
    public BatchResponse assignToBatch(String assignmentId, String batchId);
}
