package com.example.library.bookmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timeStamp, String message, String details) {
        super();
        this.timestamp = timeStamp;
        this.message = message;
        this.details = details;
    }


}
