package com.example.restservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 2xx => OK
// 4xx => client error
// 5xx => server error
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    // 예외클래스
    public UserNotFoundException(String message) {
        super(message);
    }
}
