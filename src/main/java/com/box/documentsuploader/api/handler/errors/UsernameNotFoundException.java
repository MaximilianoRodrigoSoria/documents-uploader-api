package com.box.documentsuploader.api.handler.errors;

import java.util.Map;

public class UsernameNotFoundException extends BaseException {


    public UsernameNotFoundException(String detail) {
        super(detail);
    }

    public UsernameNotFoundException(String detail, Map<String, Object> variables) {
        super(detail, variables);    }

    public UsernameNotFoundException(String detail, Map<String, Object> variables, String path) {
        super(detail, variables, path);
    }

    public UsernameNotFoundException(String detail, String path) {
        super(detail, path);
    }



}
