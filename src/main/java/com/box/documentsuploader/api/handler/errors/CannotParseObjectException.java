package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class CannotParseObjectException extends BaseException {


    public CannotParseObjectException(String detail) {
        super(detail);
    }

    public CannotParseObjectException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public CannotParseObjectException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public CannotParseObjectException(String detail, String path) {
        super(detail, path);
    }



}
