package com.scing.erp.sistema.miscelaneos.localizacion.departamento;

public class DepartamentoDTO {

  private Long iddepartamento;
  private String nombre;
  private boolean eliminado;

  public DepartamentoDTO() {
  }

  public DepartamentoDTO(Long iddepartamento) {
    this.iddepartamento = iddepartamento;
  }

  public Long getIddepartamento() {
    return iddepartamento;
  }

  public void setIddepartamento(Long iddepartamento) {
    this.iddepartamento = iddepartamento;
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
