package cn.ch1tanda.event.config;

import cn.ch1tanda.event.exception.ServiceException;
import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.convention.response.Results;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result exceptionHandler(ServiceException e) {
        return Results.failure(e.getCode(), e.getMessage());
    }

}
