package com.elearning.model.exceptions;

import lombok.Data;

import javax.ws.rs.core.Response;

@Data
public class ElearningException extends RuntimeException{
    private Response.Status status;
    private String message;

    public ElearningException() {
        super();
    }

    public ElearningException(String message) {
        super(message);
        this.message=message;
        status=Response.Status.INTERNAL_SERVER_ERROR;
    }

    public ElearningException(String message, Response.Status status) {
        super(message);
        this.message=message;
        this.status=status;
    }

    public ElearningException(String message, Throwable cause) {
        super(message,cause);
        this.message=message;
        this.status=Response.Status.INTERNAL_SERVER_ERROR;
    }

    protected ElearningException(String message, Response.Status status, Throwable cause) {
        super(message, cause);
        this.message=message;
        this.status=status;
    }

}
