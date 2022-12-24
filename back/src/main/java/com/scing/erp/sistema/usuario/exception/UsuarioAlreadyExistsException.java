package com.scing.erp.sistema.usuario.exception;

import com.scing.erp.sistema.exception.ApiException;

public class UsuarioAlreadyExistsException extends ApiException {
  private static final long serialVersionUID = -2737319632275059973L;

  public UsuarioAlreadyExistsException(String correo) {
    super("Usuario con correo: " + correo + " ya se encuentra registrado");
  }
}
