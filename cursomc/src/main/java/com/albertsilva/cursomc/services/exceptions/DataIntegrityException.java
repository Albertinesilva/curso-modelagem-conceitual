package com.albertsilva.cursomc.services.exceptions;

public class DataIntegrityException extends BusinessException {

  public DataIntegrityException(String message) {
    super(message, 409);
  }
}