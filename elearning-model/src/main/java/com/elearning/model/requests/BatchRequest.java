package com.elearning.model.requests;

import lombok.Data;

@Data
public class BatchRequest {
    private String info;
    private Long timeSlotOfDay;
    private String externalId;
}
