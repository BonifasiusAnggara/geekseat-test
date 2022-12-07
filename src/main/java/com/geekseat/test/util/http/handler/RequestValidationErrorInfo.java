package com.geekseat.test.util.http.handler;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

public class RequestValidationErrorInfo {
    private final ZonedDateTime timestamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final String message;
    private List<FieldError> fieldErrors = new ArrayList<>();

    public RequestValidationErrorInfo() {
        timestamp = null;
        this.httpStatus = null;
        this.path = null;
        this.message = null;
    }

    RequestValidationErrorInfo(HttpStatus httpStatus, String path, String message) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return httpStatus.value();
    }

    public String getError() {
        return httpStatus.getReasonPhrase();
    }

    public String getMessage() {
        return message;
    }

    public void addFieldError(String objectName, String field, String message) {
        FieldError error = new FieldError(objectName, field, message);

        fieldErrors.add(error);
    }

    public List<CustomFieldError> getErrors() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        final List<CustomFieldError> customFieldErrors = new ArrayList<CustomFieldError>();

        fieldErrors.forEach((FieldError fieldError) -> {
            CustomFieldError customFieldError = modelMapper.map(fieldError, CustomFieldError.class);
            customFieldErrors.add(customFieldError);
        });

        return customFieldErrors;
    }
}
