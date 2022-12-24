package com.scing.erp.comercial.cliente;

import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public class ClienteDTO {

  private Long idcliente;

  @NonNull
  private Long ruc;

  @NonNull
  @NotEmpty
  private String razonsocial;

  @NonNull
  @NotEmpty
  private String telefono;

  private String paginaweb;

  @NonNull
  @NotEmpty
  private String direccion;
  private boolean eliminado;

  public ClienteDTO() {
  }

  public Long getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(Long idcliente) {
    this.idcliente = idcliente;
  }

  public Long getRuc() {
    return ruc;
  }

  public void setRuc(Long ruc) {
    this.ruc = ruc;
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public String getPaginaweb() {
    return paginaweb;
  }

  public void setPaginaweb(String paginaweb) {
    this.paginaweb = paginaweb;
  }

}
