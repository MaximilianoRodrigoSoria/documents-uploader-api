package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class NotFoundException extends BaseException {


    public NotFoundException(String detail) {
        super(detail);
    }

    public NotFoundException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public NotFoundException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public NotFoundException(String detail, String path) {
        super(detail, path);
    }



}
