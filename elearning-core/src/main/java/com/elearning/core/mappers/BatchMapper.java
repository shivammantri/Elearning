package com.elearning.core.mappers;

import com.elearning.entities.Batch;
import com.elearning.model.requests.BatchRequest;
import com.elearning.model.responses.BatchResponse;
import com.google.inject.Inject;

import java.util.stream.Collectors;

public class BatchMapper {
    private final InstructorMapper instructorMapper;
    private final StudentMapper studentMapper;

    @Inject
    public BatchMapper(InstructorMapper instructorMapper, StudentMapper studentMapper) {
        this.instructorMapper = instructorMapper;
        this.studentMapper = studentMapper;
    }

    public void mapRequestToEntity(Batch batch, BatchRequest batchRequest) {
        if(batchRequest.getInfo() != null) {
            batch.setInfo(batchRequest.getInfo());
        }
        if(batchRequest.getTimeSlotOfDay() != null) {
            batch.setTimeSlotOfDay(batchRequest.getTimeSlotOfDay());
        }
    }

    public BatchResponse mapEntityToResponse(Batch batch) {
        BatchResponse batchResponse = new BatchResponse();
        batchResponse.setExternalId(batch.getExternalId());
        batchResponse.setInfo(batch.getInfo());
        batchResponse.setTimeSlotOfDay(batch.getTimeSlotOfDay());
        if(batch.getInstructor() != null) {
            batchResponse.setInstructor(instructorMapper.mapEntityToResponse(batch.getInstructor()));
        }
        if(batch.getEnrolledStudents() != null && !batch.getEnrolledStudents().isEmpty()) {
            batchResponse.setStudents(batch.getEnrolledStudents()
                    .stream()
                    .map(studentMapper::mapEntityToResponse)
                    .collect(Collectors.toList()));
        }
        return batchResponse;
    }
}
