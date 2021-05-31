package com.elearning.platform.services;

import com.elearning.entities.Student;
import com.elearning.model.requests.BatchRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.StudentResponse;

import java.util.List;

public interface BatchService {
    public BatchResponse getBatch(String batchId);
    public List<StudentResponse> getStudentsInBatch(String batchId);
    public Long getTimeSlotOfBatch(String batchId);
    public BatchResponse createBatch(BatchRequest batchRequest);
    public BatchResponse updateBatchDetails(BatchRequest batchRequest);
    public BatchResponse addStudentToBatch(String batchId, Student student);
}
