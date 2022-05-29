package com.react.api.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.react.api.exceptions.custom.BadRequestException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;



@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> validationErrorHandler(ConstraintViolationException ex, HttpServletRequest req){
    	String message = "";
        for(ConstraintViolation<?> cv : ex.getConstraintViolations()){
        	message = message + cv.getMessage();
        }
        ErrorResponse errorFound = new ErrorResponse(500, new Date(), message, req.getRequestURI());
        return new ResponseEntity<>(errorFound, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> notFoundErrorHandler(Exception ex, HttpServletRequest req){
    	ErrorResponse errorFound = new ErrorResponse(404, new Date(), ex.getMessage(),req.getRequestURI());
    	return new ResponseEntity<>(errorFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<?> badRequestErrorHandler(Exception ex, HttpServletRequest req){
    	ErrorResponse errorFound = new ErrorResponse(500, new Date(), ex.getMessage(),req.getRequestURI());
    	return new ResponseEntity<>(errorFound, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> entityNotFoundErrorHandler(Exception ex, HttpServletRequest req){
    	ErrorResponse errorFound = new ErrorResponse(400, new Date(), ex.getMessage(), req.getRequestURI());
    	return new ResponseEntity<>(errorFound, HttpStatus.NOT_FOUND);
    }

}