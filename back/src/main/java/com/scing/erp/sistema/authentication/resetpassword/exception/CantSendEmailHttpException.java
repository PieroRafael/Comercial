package com.scing.erp.sistema.authentication.resetpassword.exception;

import com.scing.erp.sistema.exception.HttpException;
import org.springframework.http.HttpStatus;

public class CantSendEmailHttpException extends HttpException {
  public CantSendEmailHttpException() {
    super("En estos momentos no se puede restablecer la contraseña, por favor inténtelo más tarde", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
