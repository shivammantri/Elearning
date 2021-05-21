package com.elearning.model.requests;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private String externalId;
    private String standard;
    private String location;
    private String email;
}
