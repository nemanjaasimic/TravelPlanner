package com.simic.travel_planner.exception;

import com.simic.travel_planner.dto.ApiResponse;
import com.simic.travel_planner.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logger.error(ex.getMessage(), ex.getCause());

        final List<String> errors = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }
        for(ObjectError error: ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ":" + error.getDefaultMessage());
        }

        final ApiResponse apiResponse = new ApiResponse(false, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiResponse, headers, status, request);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        return getObjectResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return getObjectResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return getObjectResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex) {
        return getObjectResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<Object> handleTripNotFoundException(TripNotFoundException ex) {
        return getObjectResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TripException.class)
    public ResponseEntity<Object> handleTripException(TripException ex) {
        return getObjectResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeletionException.class)
    public ResponseEntity<Object> handleDeletionException(DeletionException ex) {
        return getObjectResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return getObjectResponseEntity(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        return getObjectResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        return getObjectResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Exception ex, HttpStatus status) {
        logger.error(ex.getMessage(), ex.getCause());

        final ApiResponse apiResponse = new ApiResponse(false,
                ex.getLocalizedMessage(), getErrors(ex));
        return new ResponseEntity<>(apiResponse, new HttpHeaders(), status);
    }


    private List<String> getErrors(Exception e) {
        final List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return errors;
    }
}
