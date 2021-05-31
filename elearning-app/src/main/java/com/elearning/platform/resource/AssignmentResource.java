package com.elearning.platform.resource;

import com.elearning.entities.Assignment;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.FileStatus;
import com.elearning.platform.services.AssignmentService;
import com.google.common.base.Charsets;
import com.google.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/assignment")
public class AssignmentResource {
    private final AssignmentService assignmentService;

    @Inject
    public AssignmentResource(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @Path("/upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response uploadAssignment(@FormDataParam("file") FormDataBodyPart body,
                                     @FormDataParam("file") InputStream uploadedInputStream,
                                     @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        String fileName = fileDetail.getFileName();
        fileName = new String(fileName.getBytes(Charsets.US_ASCII));
        byte[] bytes = IOUtils.toByteArray(uploadedInputStream);
        MediaType mimeType = body.getMediaType();
        Assignment assignment = new Assignment();
        assignment.setFileData(bytes);
        assignment.setFileName(fileName);
        assignment.setFileType(mimeType.toString());
        assignment.setFileStatus(FileStatus.UPLOADED);
        assignmentService.uploadAssignment(assignment);
        return Response.ok("Assignment uploaded").build();
    }

    @Path("/download/{assignmentId}")
    @POST
    public Response downloadAssignment(@QueryParam("pathToWrite") String pathToWrite,
                                       @PathParam("assignmentId") String assignmentId) throws IOException {
        assignmentService.downloadAssignment(assignmentId, pathToWrite);
        return Response.ok("Assignment download to path :: " + pathToWrite).build();
    }

    @Path("/assign/{assignmentId}/{batchId}")
    @POST
    public BatchResponse assignToBatch(@PathParam("assignmentId") String assignmentId,
                                       @PathParam("batchId") String batchId) {
        return assignmentService.assignToBatch(assignmentId, batchId);
    }
}
