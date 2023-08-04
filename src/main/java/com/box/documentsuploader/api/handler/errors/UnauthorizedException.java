package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class UnauthorizedException extends BaseException {


    public UnauthorizedException(String detail) {
        super(detail);
    }

    public UnauthorizedException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public UnauthorizedException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public UnauthorizedException(String detail, String path) {
        super(detail, path);
    }



}
