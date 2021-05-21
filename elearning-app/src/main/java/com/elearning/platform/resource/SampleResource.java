package com.elearning.platform.resource;

import com.codahale.metrics.annotation.Timed;
import com.elearning.dao.BatchDao;
import com.elearning.entities.Batch;
import com.elearning.utility.IdGenerator;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elearning/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {
    @Path("/test")
    @GET
    @UnitOfWork
    public Response sample() {
        return Response.ok("this is test").build();
    }


    @Inject
    private BatchDao batchDao;

    @Path("/batch")
    @GET
    @UnitOfWork
    public Response sampleBatch() {
        Batch batch=new Batch();
        batch.setExternalId(IdGenerator.generateBatchId());
        batchDao.create(batch);
        return Response.ok("this is test").build();
    }
}

