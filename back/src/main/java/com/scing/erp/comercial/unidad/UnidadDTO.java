package com.scing.erp.comercial.unidad;

public class UnidadDTO {

  private Long idunidad;
  private String nombre;
  private String descripcion;
  private Long idcliente;
  private boolean eliminado;

  public UnidadDTO() {
  }

  public Long getIdunidad() {
    return idunidad;
  }

  public void setIdunidad(Long idunidad) {
    this.idunidad = idunidad;
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

  public Long getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(Long idcliente) {
    this.idcliente = idcliente;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

}
