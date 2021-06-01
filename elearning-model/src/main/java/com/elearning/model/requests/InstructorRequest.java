package com.elearning.model.requests;

import lombok.Data;


@Data
public class InstructorRequest {
    private String qualification;
    private String experience;
    private Integer feesPerHour;
    private String externalId;
    private String email;
    private String name;
}
