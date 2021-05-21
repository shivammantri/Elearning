package com.elearning.model.responses;

import lombok.Data;

import java.util.List;

@Data
public class BatchResponse {
    private String info;
    private Long timeSlotOfDay;
    private String externalId;
    private List<StudentResponse> students;
    private InstructorResponse instructorResponse;
    private List<AssignmentResponse> assignments;
}
