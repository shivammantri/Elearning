package com.elearning.platform.services;


import com.elearning.entities.Instructor;

public interface InstructorService {
    public Instructor getInstructor(String externalId);
}
