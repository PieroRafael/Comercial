package com.scing.erp.comercial.spc;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "spc")
public class Spc implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idspc;

  @Length(max = 7)
  @Column(name = "codigo", nullable = false)
  @NotEmpty(message = "Por favor , ingrese el código.")
  private String codigo;

  @Length(max = 120)
  @Column(name = "proyecto", nullable = false)
  @NotEmpty(message = "Por favor , ingrese el nombre del proyecto.")
  private String proyecto;

  @Length(max = 100)
  @Column(name = "vendedor", nullable = false)
  @NotEmpty(message = "Por favor , seleccione el nombre del vendedor.")
  private String vendedor;

  @Length(max = 30)
  @Column(name = "tipo", nullable = false)
  @NotEmpty(message = "Por favor , seleccione el tipo.")
  private String tipo;

  @ManyToOne
  @JoinColumn(name = "idcliente", nullable = false)
  private Cliente cliente;

  @Column(name = "fechaenviodeconsulta", nullable = false)
  private Date fechaenviodeconsulta;

  @Column(name = "fechaabsolucion", nullable = false)
  private Date fechaabsolucion;

  @Column(name = "fechaentrega", nullable = false)
  private Date fechaentrega;

  @Column(name = "fechareunion", nullable = false)
  private Date fechareunion;

  @Column(name = "fechavisitatecnica", nullable = false)
  private Date fechavisitatecnica;

  @Length(max = 150)
  @Column(name = "ubicacion", nullable = false)
  @NotEmpty(message = "Por favor , ingrese la ubicación.")
  private String ubicacion;

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
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación del SPC.")
  private String ucreate;

  @Length(max = 100)
  @Column(name = "uupdate", nullable = true)
  private String uupdate;

  public Spc() {
  }

  public Long getIdspc() {
    return idspc;
  }

  public void setIdspc(Long idspc) {
    this.idspc = idspc;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getProyecto() {
    return proyecto;
  }

  public void setProyecto(String proyecto) {
    this.proyecto = proyecto;
  }

  public String getVendedor() {
    return vendedor;
  }

  public void setVendedor(String vendedor) {
    this.vendedor = vendedor;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
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

  public Date getFechaenviodeconsulta() {
    return fechaenviodeconsulta;
  }

  public void setFechaenviodeconsulta(Date fechaenviodeconsulta) {
    this.fechaenviodeconsulta = fechaenviodeconsulta;
  }

  public Date getFechaabsolucion() {
    return fechaabsolucion;
  }

  public void setFechaabsolucion(Date fechaabsolucion) {
    this.fechaabsolucion = fechaabsolucion;
  }

  public Date getFechaentrega() {
    return fechaentrega;
  }

  public void setFechaentrega(Date fechaentrega) {
    this.fechaentrega = fechaentrega;
  }

  public Date getFechareunion() {
    return fechareunion;
  }

  public void setFechareunion(Date fechareunion) {
    this.fechareunion = fechareunion;
  }

  public Date getFechavisitatecnica() {
    return fechavisitatecnica;
  }

  public void setFechavisitatecnica(Date fechavisitatecnica) {
    this.fechavisitatecnica = fechavisitatecnica;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

}
