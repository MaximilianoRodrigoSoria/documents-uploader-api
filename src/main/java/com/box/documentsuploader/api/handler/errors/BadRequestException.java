package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class BadRequestException extends BaseException {


    public BadRequestException(String detail) {
        super(detail);
    }

    public BadRequestException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public BadRequestException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public BadRequestException(String detail, String path) {
        super(detail, path);
    }



}
