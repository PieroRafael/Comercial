package com.scing.erp.sistema.miscelaneos.localizacion.provincia;

public class ProvinciaDTO {

  private Long idprovincia;
  private String nombre;
  private boolean eliminado;

  public ProvinciaDTO() {
  }

  public ProvinciaDTO(Long idprovincia) {
    this.idprovincia = idprovincia;
  }

  public Long getIdprovincia() {
    return idprovincia;
  }

  public void setIdprovincia(Long idprovincia) {
    this.idprovincia = idprovincia;
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
