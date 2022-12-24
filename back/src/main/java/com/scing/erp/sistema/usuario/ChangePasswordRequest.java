package com.scing.erp.sistema.usuario;

public class ChangePasswordRequest {

  private Usuario usuario;
  private String password;

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
