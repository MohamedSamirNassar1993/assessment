package com.stc.assessment.exception;

import org.springframework.http.HttpStatus;

public class AssessmentException extends BusinessException {

    public AssessmentException(HttpStatus status) {
        super(status);
    }

    public AssessmentException(String message, HttpStatus status) {
        super(message, status);
    }
}
