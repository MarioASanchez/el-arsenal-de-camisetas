package com.elarsenaldecamisetas.app.exception;

public class UnauthorizedException extends  RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
