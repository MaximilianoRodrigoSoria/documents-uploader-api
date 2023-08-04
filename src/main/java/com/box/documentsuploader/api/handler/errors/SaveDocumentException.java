package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class SaveDocumentException extends BaseException {


    public SaveDocumentException(String detail) {
        super(detail);
    }

    public SaveDocumentException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public SaveDocumentException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public SaveDocumentException(String detail, String path) {
        super(detail, path);
    }



}
