package com.scing.erp.sistema.authentication.exception;

public class TokenValidationException extends AuthenticationException {

  public TokenValidationException(String message) {
    super(message);
  }

  public TokenValidationException() {
  }
}
