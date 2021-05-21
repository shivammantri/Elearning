package com.elearning.platform.services;

import com.elearning.entities.Batch;
import com.elearning.model.responses.BatchResponse;

public interface BatchService {
    public BatchResponse getBatch(String externalId);
}
