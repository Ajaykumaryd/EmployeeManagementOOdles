package com.Task1.OodlesA1.Controller;

import com.Task1.OodlesA1.Exceptions.CompanyIsNotPresent;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeException;
import com.Task1.OodlesA1.Exceptions.ErrorMessages;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@ResponseStatus
public class MyAdviceController extends ResponseEntityExceptionHandler {

    //Inbuilt Exception
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(NoSuchElementException noSuchElementException){
    return new ResponseEntity<>("Company is not present with this ID",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyIsNotPresent.class)
    public ResponseEntity<ErrorMessages> handleEmployeeException(CompanyIsNotPresent ex) {
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setMessage(ex.getMessage());
        errorMessages.setHTTPStatus(1117);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(DepartmentIsNotPresent.class)
    public ResponseEntity<ErrorMessages>departmentAbsent(DepartmentIsNotPresent ex){
        ErrorMessages errorMessages=new ErrorMessages();
        errorMessages.setMessage(ex.getMessage());
        errorMessages.setHTTPStatus(1117);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorMessages> handleEmployeeException(EmployeeException ex) {
     ErrorMessages errorMessages=new ErrorMessages();
     errorMessages.setMessage(ex.getMessage());
     errorMessages.setHTTPStatus(1117);
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

}

