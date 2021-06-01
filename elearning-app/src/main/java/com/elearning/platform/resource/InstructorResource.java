package com.elearning.platform.resource;

import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.requests.InstructorRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.InstructorResponse;
import com.elearning.model.responses.StudentResponse;
import com.elearning.platform.services.InstructorService;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/instructor")
@Produces(MediaType.APPLICATION_JSON)
public class InstructorResource {
    private final InstructorService instructorService;

    @Inject
    public InstructorResource(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Path("/add/")
    @POST
    @UnitOfWork
    public InstructorResponse addInstructor(InstructorRequest instructorRequest) {
        return instructorService.addInstructor(instructorRequest);
    }

    @Path("/update/{instructorId}")
    @POST
    @UnitOfWork
    public InstructorResponse updateInstructorDetails(InstructorRequest instructorRequest,
                                                      @PathParam("instructorId") String instructorId) {
        if(instructorId == null || !instructorId.equalsIgnoreCase(instructorRequest.getExternalId())) {
            throw new ElearningException("Instructor ids doesn't match in request");
        }
        return instructorService.updateInstructorDetails(instructorRequest);
    }

    @Path("/{instructorId}")
    @GET
    @UnitOfWork
    public InstructorResponse getInstructorDetails(@PathParam("instructorId") String instructorId) {
        return instructorService.getInstructor(instructorId);
    }

    @Path("/batch/{instructorId}/{batchId}")
    @POST
    @UnitOfWork
    public BatchResponse allocateBatch(@PathParam("instructorId") String instructorId,
                                            @PathParam("batchId") String batchId) {
        return instructorService.allocateBatch(instructorId, batchId);
    }

    @Path("/batches/{instructorId}")
    @GET
    @UnitOfWork
    public List<BatchResponse> getListOfAllocatedBatches(@PathParam("instructorId") String instructorId) {
        return instructorService.getAllocatedBatches(instructorId);
    }
}
