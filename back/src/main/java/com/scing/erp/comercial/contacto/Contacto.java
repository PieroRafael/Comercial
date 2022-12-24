package com.scing.erp.comercial.contacto;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.scing.erp.comercial.cliente.Cliente;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "contacto")
public class Contacto implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcontacto;

  @Length(max = 100)
  @Column(name = "nombre", nullable = false)
  @NotEmpty(message = "Por favor , ingrese los nombres del contacto.")
  private String nombre;

  @Length(max = 100)
  @Column(name = "correo", nullable = false)
  @NotEmpty(message = "Por favor , ingrese el correo.")
  private String correo;

  @Length(max = 9)
  @Column(name = "telefono", nullable = true)
  private String telefono;

  @Length(max = 9)
  @Column(name = "celular", nullable = false)
  @NotEmpty(message = "Por favor , ingrese el celular.")
  private String celular;

  @Length(max = 100)
  @Column(name = "cargo", nullable = true)
  private String cargo;

  /* Unidad */
  @Column(name = "unidad", nullable = true)
  private String unidad;

  @ManyToOne
  @JoinColumn(name = "idcliente", nullable = false)
  private Cliente cliente;

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
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación del contacto.")
  private String ucreate;

  @Length(max = 100)
  @Column(name = "uupdate", nullable = true)
  private String uupdate;

  public Contacto() {
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

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
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
