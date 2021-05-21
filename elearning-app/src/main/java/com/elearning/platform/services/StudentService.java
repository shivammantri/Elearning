package com.elearning.platform.services;

import com.elearning.entities.Student;
import com.elearning.model.responses.StudentResponse;

public interface StudentService {
    public StudentResponse getStudent(String externalId);
}
