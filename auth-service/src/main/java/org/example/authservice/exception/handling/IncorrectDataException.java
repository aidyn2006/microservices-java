package org.example.authservice.exception.handling;

public class IncorrectDataException extends RuntimeException{
    public IncorrectDataException(String message){
        super(message);
    }
}
