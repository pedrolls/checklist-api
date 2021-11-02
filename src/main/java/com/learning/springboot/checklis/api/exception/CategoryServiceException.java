package com.learning.springboot.checklis.api.exception;

public class CategoryServiceException extends  RuntimeException{

    public CategoryServiceException(String message){
        super(message);
    }
}
