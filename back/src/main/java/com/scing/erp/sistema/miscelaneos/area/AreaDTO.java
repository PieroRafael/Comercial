package com.scing.erp.sistema.miscelaneos.area;

public class AreaDTO {

	private Long idarea;
  private String nombre;
  private String descripcion;
  private boolean eliminado;

  public AreaDTO() {
  }

  public Long getIdarea() {
    return idarea;
  }

  public void setIdarea(Long idarea) {
    this.idarea = idarea;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }
}
