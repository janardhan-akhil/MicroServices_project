package com.user_service.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found on server!!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
