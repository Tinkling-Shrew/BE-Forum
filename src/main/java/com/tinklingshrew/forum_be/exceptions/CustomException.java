package com.tinklingshrew.forum_be.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public CustomException(HttpStatus httpStatus)
    {
        this(httpStatus,null,null);
    }

    public CustomException(HttpStatus httpStatus,String reason)
    {
        this(httpStatus,reason,null);
    }

    public CustomException(HttpStatus httpStatus,String reason,Throwable cause)
    {
        super(reason,cause);
        this.httpStatus = httpStatus;
    }
}
