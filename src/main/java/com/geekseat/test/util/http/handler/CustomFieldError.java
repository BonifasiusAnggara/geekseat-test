package com.geekseat.test.util.http.handler;

import java.io.Serializable;

public class CustomFieldError implements Serializable {
    private String field;
    private String defaultMessage;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
