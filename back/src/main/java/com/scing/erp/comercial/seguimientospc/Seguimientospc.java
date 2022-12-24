package com.scing.erp.comercial.seguimientospc;

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
import com.scing.erp.comercial.spc.Spc;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "seguimientospc")
public class Seguimientospc implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idseguimientospc;

  @Length(max = 30)
  @Column(name = "formarecepcion", nullable = false)
  @NotEmpty(message = "Por favor, seleecione forma de recepción.")
  private String formarecepcion;

  @Length(max = 200)
  @Column(name = "descripcion", nullable = false)
  @NotEmpty(message = "Por favor, ingrese una descripción.")
  private String descripcion;

  @Length(max = 100)
  @Column(name = "observacion", nullable = false)
  @NotEmpty(message = "Por favor, ingrese una observación.")
  private String observacion;

  @ManyToOne
  @JoinColumn(name = "idspc", nullable = false)
  private Spc spc;

  @CreationTimestamp
  @Column(name = "fcreate")
  private LocalDateTime fcreate;

  @UpdateTimestamp
  @Column(name = "fupdate", nullable = true)
  private LocalDateTime fupdate;

  @Length(max = 100)
  @Column(name = "ucreate", nullable = false)
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación del seguimiento.")
  private String ucreate;

  @Length(max = 100)
  @Column(name = "uupdate", nullable = true)
  private String uupdate;

  public Seguimientospc() {
  }

  public Long getIdseguimientospc() {
    return idseguimientospc;
  }

  public void setIdseguimientospc(Long idseguimientospc) {
    this.idseguimientospc = idseguimientospc;
  }

  public String getFormarecepcion() {
    return formarecepcion;
  }

  public void setFormarecepcion(String formarecepcion) {
    this.formarecepcion = formarecepcion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public Spc getSpc() {
    return spc;
  }

  public void setSpc(Spc spc) {
    this.spc = spc;
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

}
