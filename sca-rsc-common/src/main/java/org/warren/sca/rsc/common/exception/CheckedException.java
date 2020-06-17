package org.warren.sca.rsc.common.exception;


public class CheckedException extends RuntimeException {

    private String message;

    public CheckedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
