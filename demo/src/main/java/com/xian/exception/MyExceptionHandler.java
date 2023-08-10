package com.xian.exception;

import com.xian.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lgx
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result handlerException(ServiceException e) {
        return Result.error(e.getMessage());
    }
}
