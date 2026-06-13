package com.iker.springboot_error.exceptions;

public class UserNotFoundException extends RuntimeException{
    // Constructor
    public UserNotFoundException(String message){
        super(message);
    }
}
