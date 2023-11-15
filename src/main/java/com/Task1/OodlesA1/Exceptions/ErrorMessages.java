package com.Task1.OodlesA1.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ErrorMessages {

    private String message;
    private Integer HTTPStatus;
}
