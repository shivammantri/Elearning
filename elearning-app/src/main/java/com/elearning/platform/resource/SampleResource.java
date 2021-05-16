package com.elearning.platform.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elearning")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {
    @Path("/sample")
    @GET
    @Timed(absolute = true,name="com.tracker.com.platform.bootstrap.resource.SampleResource.sample")
    @UnitOfWork
    public Response sample() {
        return Response.ok("this is test").build();
    }
}

