package org.warren.sca.rsc.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.warren.sca.rsc.common.exception.AuthenticationFailedException;
import org.warren.sca.rsc.common.exception.CheckedException;
import org.warren.sca.rsc.gateway.vo.ResultVO;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultVO authenticationFailed(AuthenticationFailedException e){
        return ResultVO.getFailure(e.getMessage());
    }

    @ExceptionHandler(CheckedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResultVO checkedExceptionHandler(CheckedException e){
        return ResultVO.getFailure(e.getMessage());
    }
}
