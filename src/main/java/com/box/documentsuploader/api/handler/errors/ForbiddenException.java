package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class ForbiddenException extends BaseException {


    public ForbiddenException(String detail) {
        super(detail);
    }

    public ForbiddenException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public ForbiddenException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public ForbiddenException(String detail, String path) {
        super(detail, path);
    }



}
