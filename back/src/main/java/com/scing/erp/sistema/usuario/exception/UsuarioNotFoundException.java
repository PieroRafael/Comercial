package com.scing.erp.sistema.usuario.exception;

import com.scing.erp.sistema.exception.ApiException;

public class UsuarioNotFoundException extends ApiException {
	private static final long serialVersionUID = -5258959358527382145L;

	public UsuarioNotFoundException(String message) {
		super(message);
	}
}
