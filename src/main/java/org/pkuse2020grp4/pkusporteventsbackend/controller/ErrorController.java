package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.exception.NoTokenException;
import org.pkuse2020grp4.pkusporteventsbackend.exception.PermissionDeniedException;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        return new Result(-1, e.getClass().getSimpleName() + ": " + e.getMessage(), null);
    }
    @ExceptionHandler(NoTokenException.class)
    public Result handlerNoTokenException(NoTokenException e){
        return new Result(1, e.getMessage(), null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Result handlerUserNotFoundException(UserNotFoundException e){
        return new Result(1, e.getMessage(), null);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public Result handlerPermissionDeniedException(PermissionDeniedException e) { return new Result(1, e.getMessage(), null); }
}
