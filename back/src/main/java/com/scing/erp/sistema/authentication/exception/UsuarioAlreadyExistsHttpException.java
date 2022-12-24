package com.scing.erp.sistema.authentication.exception;

import com.scing.erp.sistema.exception.HttpException;
import org.springframework.http.HttpStatus;

public class UsuarioAlreadyExistsHttpException extends HttpException {
  private static final long serialVersionUID = -5202433948475658078L;

  public UsuarioAlreadyExistsHttpException() {
    super("El usuario ya existe", HttpStatus.CONFLICT);
  }
}
