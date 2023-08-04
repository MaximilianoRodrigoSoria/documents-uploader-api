package com.box.documentsuploader.api.handler;


import com.box.documentsuploader.api.handler.errors.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.Map;

@Getter
@ToString
class ErrorMessage {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final long timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Integer status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,Object> variables;

    ErrorMessage(Exception exception, Integer status) {
        this.timestamp = Instant.now().toEpochMilli();
        this.message = exception.getMessage();
        this.status = status;
    }

    ErrorMessage(BaseException exception, Integer status, Map<String,Object> variables, String message) {
        this.timestamp = Instant.now().toEpochMilli();
        this.message = message;
        this.status = status;
        this.variables = variables;

    }

    ErrorMessage(BaseException exception, Integer code, Map<String,Object> variables, String message, String path) {
        this.timestamp = Instant.now().toEpochMilli();
        this.message = message;
        this.status = code;
        this.variables = variables;
        this.path = path;
    }

    ErrorMessage(BaseException exception, Integer status, String message, String path) {
        this.timestamp = Instant.now().toEpochMilli();
        this.message = message;
        this.status = status;
        this.path = path;
    }
}
