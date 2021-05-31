package com.elearning.platform.resource;

import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.requests.BatchRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.StudentResponse;
import com.elearning.platform.services.BatchService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/batch")
@Produces(MediaType.APPLICATION_JSON)
public class BatchResource {
    private final BatchService batchService;

    @Inject
    public BatchResource(BatchService batchService) {
        this.batchService = batchService;
    }

    @Path("/add/")
    @POST
    public BatchResponse addBatch(BatchRequest batchRequest) {
        return batchService.createBatch(batchRequest);
    }

    @Path("/update/{batchId}")
    @POST
    public BatchResponse updateBatchDetails(BatchRequest batchRequest,
                                                      @PathParam("batchId") String batchId) {
        if(batchId == null || !batchId.equalsIgnoreCase(batchRequest.getExternalId())) {
            throw new ElearningException("Batch ids doesn't match in request");
        }
        return batchService.updateBatchDetails(batchRequest);
    }

    @Path("/students/{batchId}")
    @GET
    public List<StudentResponse> getStudentsInBatch(@PathParam("batchId") String batchId) {
        return batchService.getStudentsInBatch(batchId);
    }

    @Path("/timeSlot/{batchId}")
    @GET
    public Long getTimeSlotOfBatch(@PathParam("batchId") String batchId) {
        return batchService.getTimeSlotOfBatch(batchId);
    }

    @Path("/{batchId}")
    @GET
    public BatchResponse getBatchDetails(@PathParam("batchId") String batchId) {
        return batchService.getBatch(batchId);
    }
}
