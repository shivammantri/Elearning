package com.elearning.core.mappers;

import com.elearning.entities.Assignment;
import com.elearning.model.responses.AssignmentResponse;

public class AssignmentMapper {
    public AssignmentResponse mapEntityToResponse(Assignment assignment) {
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        assignmentResponse.setExternalId(assignment.getExternalId());
        assignmentResponse.setFileName(assignment.getFileName());
        assignmentResponse.setFileType(assignment.getFileType());
        return assignmentResponse;
    }
}
