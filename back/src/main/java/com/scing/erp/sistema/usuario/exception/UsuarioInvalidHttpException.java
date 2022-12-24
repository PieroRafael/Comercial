package com.scing.erp.sistema.usuario.exception;

import com.scing.erp.sistema.exception.HttpException;
import org.springframework.http.HttpStatus;

public class UsuarioInvalidHttpException extends HttpException {

	private static final long serialVersionUID = 2401650728998512026L;

	public UsuarioInvalidHttpException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

}
