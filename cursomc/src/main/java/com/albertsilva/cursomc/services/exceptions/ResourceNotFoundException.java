package com.albertsilva.cursomc.services.exceptions;

public class ResourceNotFoundException extends BusinessException {

  public ResourceNotFoundException(String message) {
    super(message, 404);
  }
}