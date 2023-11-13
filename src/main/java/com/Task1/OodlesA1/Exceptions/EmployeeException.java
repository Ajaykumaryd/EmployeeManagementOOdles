package com.Task1.OodlesA1.Exceptions;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Getter
public class EmployeeException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public EmployeeException(String message) {
        super(message);

    }

}
