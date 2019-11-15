package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 〈全局异常处理〉<br>
 *
 * @className: BaseExceptionHandler
 * @package: com.tensquare.base.controller
 * @author: admin
 * @date: 2019/11/15 15:19
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR.getCode(), e.getMessage());
    }
}
