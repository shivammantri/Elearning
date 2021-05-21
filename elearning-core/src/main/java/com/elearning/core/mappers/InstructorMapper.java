package com.elearning.core.mappers;

import com.elearning.entities.Instructor;
import com.elearning.model.requests.InstructorRequest;
import com.elearning.model.responses.InstructorResponse;

public class InstructorMapper {
    public Instructor mapRequestToEntity(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        if(instructorRequest.getExperience() != null) {
            instructor.setExperience(instructorRequest.getExperience());
        }
        if(instructorRequest.getEmail() != null) {
            instructor.setEmail(instructor.getEmail());
        }
        if(instructorRequest.getName() != null) {
            instructor.setName(instructor.getName());
        }
        if(instructorRequest.getFeesPerHour() != null) {
            instructor.setFeesPerHour(instructorRequest.getFeesPerHour());
        }
        if(instructorRequest.getQualification() != null) {
            instructor.setQualification(instructorRequest.getQualification());
        }
        return instructor;
    }

    public InstructorResponse mapEntityToResponse(Instructor instructor) {
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setEmail(instructor.getEmail());
        instructorResponse.setExperience(instructor.getExperience());
        instructorResponse.setExternalId(instructor.getExternalId());
        instructorResponse.setFeesPerHour(instructor.getFeesPerHour());
        instructorResponse.setName(instructor.getName());
        instructorResponse.setQualification(instructor.getQualification());
        return instructorResponse;
    }
}
