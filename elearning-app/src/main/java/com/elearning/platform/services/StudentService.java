package com.elearning.platform.services;

import com.elearning.model.requests.StudentRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.StudentResponse;

import java.util.List;

public interface StudentService {
    public StudentResponse getStudent(String externalId);
    public StudentResponse createStudent(StudentRequest studentRequest);
    public StudentResponse updateStudentDetails(StudentRequest studentRequest);
    public BatchResponse enrollToBatch(String batchId, String studentId);
    public StudentResponse unenrollFromBatch(String batchId, String studentId);
    public List<BatchResponse> getEnrolledBatches(String studentId);

}
