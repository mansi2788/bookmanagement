package com.example.library.bookmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND) //to send response status 404
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }

}
