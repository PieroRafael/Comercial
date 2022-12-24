package com.scing.erp.sistema.miscelaneos.localizacion.distrito;

public class DistritoDTO {

  private Long iddistrito;
  private String nombre;
  private boolean eliminado;

  public DistritoDTO() {
  }

  public DistritoDTO(Long iddistrito) {
    this.iddistrito = iddistrito;
  }

  public Long getIddistrito() {
    return iddistrito;
  }

  public void setIddistrito(Long iddistrito) {
    this.iddistrito = iddistrito;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }
}
