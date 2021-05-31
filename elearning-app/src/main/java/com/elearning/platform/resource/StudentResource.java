package com.elearning.platform.resource;

import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.requests.StudentRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.StudentResponse;
import com.elearning.platform.services.StudentService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    private final StudentService studentService;

    @Inject
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("/add/")
    @POST
    public StudentResponse addStudent(StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }

    @Path("/{studentId}")
    @GET
    public StudentResponse getStudentDetails(@PathParam("studentId") String studentId) {
        return studentService.getStudent(studentId);
    }

    @Path("/update/{studentId}")
    @POST
    public StudentResponse updateStudentDetails(StudentRequest studentRequest,
                                                      @PathParam("studentId") String studentId) {
        if(studentId == null || !studentId.equalsIgnoreCase(studentRequest.getExternalId())) {
            throw new ElearningException("Student ids doesn't match in request");
        }
        return studentService.updateStudentDetails(studentRequest);
    }

    @Path("/enroll/{studentId}/{batchId}")
    @POST
    public BatchResponse enrollToBatch(@PathParam("studentId") String studentId, @PathParam("batchId") String batchId) {
        return studentService.enrollToBatch(batchId, studentId);
    }

    @Path("/unenroll/{studentId}/{batchId}")
    @POST
    public StudentResponse unenrollFromBatch(@PathParam("studentId") String studentId, @PathParam("batchId") String batchId) {
        return studentService.unenrollFromBatch(batchId, studentId);
    }

    @Path("/batches/{studentId}")
    @GET
    public List<BatchResponse> getEnrolledBatches(@PathParam("studentId") String studentId) {
        return studentService.getEnrolledBatches(studentId);
    }
}
