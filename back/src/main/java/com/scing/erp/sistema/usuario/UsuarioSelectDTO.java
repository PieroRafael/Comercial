package com.scing.erp.sistema.usuario;

public class UsuarioSelectDTO {

  private Long idusuario;
  private String nombre;

  UsuarioSelectDTO() {
  }

  public Long getIdusuario() {
    return idusuario;
  }

  public void setIdusuario(Long idusuario) {
    this.idusuario = idusuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
