package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class BaseException extends RuntimeException {

    private Map<String, Object> variables;
    private String message;
    private String path;

    public BaseException(String detail) {
        this.message = detail;
    }

    public BaseException(String detail, Map<String, Object> variables) {
        this.variables = variables;
        this.message =  detail;
    }

    public BaseException(String detail, Map<String, Object> variables, String path) {
        this.variables = variables;
        this.message =  detail;
        this.path = path;
    }

    public BaseException(String detail, String path) {
        this.message = detail;
        this.path =  path;

    }




    public Map<String, Object> getVariables() {
         return this.variables;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath(){
        return this.path;
    }
}
