package com.elearning.model.responses;

import lombok.Data;

@Data
public class StudentResponse {
    private String externalId;
    private String standard;
    private String location;
    private Double walletBalance;
    private String email;
    private String name;
}
