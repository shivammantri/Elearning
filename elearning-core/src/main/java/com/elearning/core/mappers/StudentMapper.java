package com.elearning.core.mappers;

import com.elearning.entities.Student;
import com.elearning.model.requests.StudentRequest;
import com.elearning.model.responses.StudentResponse;

public class StudentMapper {
    public Student mapRequestToEntity(StudentRequest studentRequest) {
        Student student = new Student();
        if(studentRequest.getLocation() != null) {
            student.setLocation(studentRequest.getLocation());
        }
        if(studentRequest.getStandard() != null) {
            student.setStandard(studentRequest.getStandard());
        }
        if(studentRequest.getEmail() != null) {
            student.setEmail(studentRequest.getEmail());
        }
        return student;
    }

    public StudentResponse mapEntityToResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setEmail(student.getEmail());
        studentResponse.setExternalId(student.getExternalId());
        studentResponse.setLocation(student.getLocation());
        studentResponse.setName(student.getName());
        studentResponse.setStandard(student.getStandard());
        studentResponse.setWalletBalance(student.getWalletBalance());
        return studentResponse;
    }
}
