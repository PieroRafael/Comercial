package com.scing.erp.comercial.contacto;

import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public class ContactoDTO {

  private Long idcontacto;

  @NonNull
  @NotEmpty
  private String nombre;

  @NonNull
  @NotEmpty
  private String correo;

  @NonNull
  @NotEmpty
  private String telefono;

  private boolean eliminado;
  private String razonsocial;
  private Long idcliente;

  private String celular;
  private String cargo;

  /* Unidad */
  private String unidad;

  public ContactoDTO() {
  }

  public Long getIdcontacto() {
    return idcontacto;
  }

  public void setIdcontacto(Long idcontacto) {
    this.idcontacto = idcontacto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

  public Long getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(Long idcliente) {
    this.idcliente = idcliente;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public String getUnidad() {
    return unidad;
  }

  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }

}
