package com.elearning.platform.services;


import com.elearning.model.requests.InstructorRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.InstructorResponse;

import java.util.List;

public interface InstructorService {
    public InstructorResponse getInstructor(String externalId);
    public InstructorResponse updateInstructorDetails(InstructorRequest instructorRequest);
    public InstructorResponse addInstructor(InstructorRequest instructorRequest);
    public List<BatchResponse> getAllocatedBatches(String instructorId);
}
