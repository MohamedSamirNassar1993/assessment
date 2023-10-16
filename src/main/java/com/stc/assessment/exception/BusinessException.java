package com.stc.assessment.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class BusinessException extends RuntimeException implements GraphQLError {

    private String message;
    private HttpStatus status;
    public BusinessException(HttpStatus status) {
        this.status = status;
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }


}
