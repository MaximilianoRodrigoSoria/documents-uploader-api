package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class BadGatewayException extends BaseException {


    public BadGatewayException(String detail) {
        super(detail);
    }

    public BadGatewayException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public BadGatewayException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public BadGatewayException(String detail, String path) {
        super(detail, path);
    }



}
