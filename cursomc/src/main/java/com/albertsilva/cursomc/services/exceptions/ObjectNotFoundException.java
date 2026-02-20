package com.albertsilva.cursomc.services.exceptions;

public class ObjectNotFoundException extends BusinessException {

  public ObjectNotFoundException(String message) {
    super(message, 404);
  }
}