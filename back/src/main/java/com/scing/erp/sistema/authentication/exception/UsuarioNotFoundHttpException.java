package com.scing.erp.sistema.authentication.exception;

import com.scing.erp.sistema.exception.HttpException;
import org.springframework.http.HttpStatus;

public class UsuarioNotFoundHttpException extends HttpException {
  private static final long serialVersionUID = 4770986620665158856L;

  public UsuarioNotFoundHttpException(String message, HttpStatus status) {
    super(message, status);
  }
}
