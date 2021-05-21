package com.elearning.platform.services;


import com.elearning.entities.Instructor;
import com.elearning.model.responses.InstructorResponse;

public interface InstructorService {
    public InstructorResponse getInstructor(String externalId);
}
