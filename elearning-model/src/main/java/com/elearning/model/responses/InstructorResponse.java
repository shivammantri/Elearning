package com.elearning.model.responses;

import lombok.Data;

@Data
public class InstructorResponse {
    private String qualification;
    private String experience;
    private Integer feesPerHour;
    private String externalId;
    private String email;
    private String name;
}
