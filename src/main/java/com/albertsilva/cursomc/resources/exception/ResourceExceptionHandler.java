package com.albertsilva.cursomc.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
    StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }
}
