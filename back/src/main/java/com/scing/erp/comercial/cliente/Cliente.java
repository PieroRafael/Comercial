package com.scing.erp.comercial.cliente;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcliente;

  @Column(name = "ruc", nullable = false, unique = true)
  private Long ruc;

  @Length(max = 120)
  @Column(name = "razonsocial", nullable = false)
  @NotEmpty(message = "Por favor , ingrese la razón social.")
  private String razonsocial;

  @Length(max = 9)
  @Column(name = "telefono", nullable = false)
  @NotEmpty(message = "Por favor , ingrese el teléfono.")
  private String telefono;

  @Length(max = 150)
  @Column(name = "direccion", nullable = false)
  @NotEmpty(message = "Por favor , ingrese la dirección.")
  private String direccion;

  @Column(name = "paginaweb", nullable = true)
  private String paginaweb;

  @Column(name = "eliminado", nullable = false)
  private boolean eliminado;

  @CreationTimestamp
  @Column(name = "fcreate")
  private LocalDateTime fcreate;

  @UpdateTimestamp
  @Column(name = "fupdate", nullable = true)
  private LocalDateTime fupdate;

  @Length(max = 100)
  @Column(name = "ucreate", nullable = false)
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación del cliente.")
  private String ucreate;

  @Length(max = 100)
  @Column(name = "uupdate", nullable = true)
  private String uupdate;

  public Cliente() {
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

  public LocalDateTime getFcreate() {
    return fcreate;
  }

  public void setFcreate(LocalDateTime fcreate) {
    this.fcreate = fcreate;
  }

  public LocalDateTime getFupdate() {
    return fupdate;
  }

  public void setFupdate(LocalDateTime fupdate) {
    this.fupdate = fupdate;
  }

  public String getUcreate() {
    return ucreate;
  }

  public void setUcreate(String ucreate) {
    this.ucreate = ucreate;
  }

  public String getUupdate() {
    return uupdate;
  }

  public void setUupdate(String uupdate) {
    this.uupdate = uupdate;
  }

  public String getPaginaweb() {
    return paginaweb;
  }

  public void setPaginaweb(String paginaweb) {
    this.paginaweb = paginaweb;
  }

}
