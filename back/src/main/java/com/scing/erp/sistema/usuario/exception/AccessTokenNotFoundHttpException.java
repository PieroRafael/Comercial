package com.scing.erp.sistema.usuario.exception;

import com.scing.erp.sistema.exception.HttpException;
import org.springframework.http.HttpStatus;

public class AccessTokenNotFoundHttpException extends HttpException {

    public AccessTokenNotFoundHttpException(String message, HttpStatus status) {
        super(message, status);
    }
}
