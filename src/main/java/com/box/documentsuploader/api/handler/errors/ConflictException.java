package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class ConflictException extends BaseException {


    public ConflictException(String detail) {
        super(detail);
    }

    public ConflictException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public ConflictException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public ConflictException(String detail, String path) {
        super(detail, path);
    }



}
