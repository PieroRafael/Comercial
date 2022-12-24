package com.scing.erp.sistema.authentication.resetpassword.exception;

import com.scing.erp.sistema.exception.HttpException;
import org.springframework.http.HttpStatus;

public class IncorrectEmailHttpException extends HttpException {
  public IncorrectEmailHttpException() {
    super("Correo inválido", HttpStatus.FORBIDDEN);
  }
}
