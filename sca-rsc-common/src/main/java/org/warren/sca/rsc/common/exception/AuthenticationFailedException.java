package org.warren.sca.rsc.common.exception;

/*
    为什么要有这个类？目的是为了在抛出异常后能够自定义响应状态码为403
 */
public class AuthenticationFailedException extends CheckedException {

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
