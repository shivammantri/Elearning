package com.elearning.platform.services;

import com.elearning.entities.Assignment;
import com.elearning.model.responses.BatchResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AssignmentService {
    public void uploadAssignment(Assignment assignment);
    public void downloadAssignment(String assignmentId, String pathToWrite) throws IOException;
    public BatchResponse assignToBatch(String assignmentId, String batchId);
}
