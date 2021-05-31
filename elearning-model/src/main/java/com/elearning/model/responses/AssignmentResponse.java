package com.elearning.model.responses;

import lombok.Data;

@Data
public class AssignmentResponse {
    private String associatedBatchId;
    private String createdBy;
    private String externalId;
    private String filePath;
    private String fileName;
    private String fileType;
    private FileStatus fileStatus;
}
