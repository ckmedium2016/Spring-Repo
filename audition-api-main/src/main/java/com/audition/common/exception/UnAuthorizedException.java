package com.audition.common.exception;

public class UnAuthorizedException extends SystemException {

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
    }
}