package com.scing.erp.sistema.exception;

public class JsonParseException extends RuntimeException {

  public JsonParseException(String message, Throwable e) {
    super(message, e);
  }
}
