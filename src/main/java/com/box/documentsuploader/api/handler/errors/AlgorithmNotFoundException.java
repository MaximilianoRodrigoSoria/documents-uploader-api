package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class AlgorithmNotFoundException extends BaseException {


    public AlgorithmNotFoundException(String detail) {
        super(detail);
    }

    public AlgorithmNotFoundException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public AlgorithmNotFoundException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public AlgorithmNotFoundException(String detail, String path) {
        super(detail, path);
    }



}
