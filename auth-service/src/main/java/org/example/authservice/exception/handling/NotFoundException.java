package org.example.authservice.exception.handling;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
