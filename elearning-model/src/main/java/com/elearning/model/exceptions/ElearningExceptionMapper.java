package com.elearning.model.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class ElearningExceptionMapper implements ExceptionMapper<ElearningException>{

    @Override
    public Response toResponse(ElearningException exception){
        log.error("Error while processing the request, error is : " + exception.getMessage(), exception);
        Response.Status status = exception.getStatus();
        if (status == null) {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        String transactionId = MDC.get("id");
        ExceptionResponse exceptionResponse = new ExceptionResponse(transactionId,status, ElearningException.class.getName(), exception.getMessage());
        return Response.status(exceptionResponse.getStatus()).entity(exceptionResponse).build();
    }
}
