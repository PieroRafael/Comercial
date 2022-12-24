package com.scing.erp.comercial.documentospc;

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
import com.scing.erp.comercial.seguimientospc.Seguimientospc;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "documentospc")
public class Documentospc implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iddocumentospc;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "url", nullable = false)
  private String url;

  @ManyToOne
  @JoinColumn(name = "idseguimientospc", nullable = false)
  private Seguimientospc seguimientospc;

  @CreationTimestamp
  @Column(name = "fcreate")
  private LocalDateTime fcreate;

  @Length(max = 100)
  @Column(name = "ucreate", nullable = false)
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación del documento.")
  private String ucreate;

  public Documentospc() {
  }

  public Long getIddocumentospc() {
    return iddocumentospc;
  }

  public void setIddocumentospc(Long iddocumentospc) {
    this.iddocumentospc = iddocumentospc;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Seguimientospc getSeguimientospc() {
    return seguimientospc;
  }

  public void setSeguimientospc(Seguimientospc seguimientospc) {
    this.seguimientospc = seguimientospc;
  }

  public LocalDateTime getFcreate() {
    return fcreate;
  }

  public void setFcreate(LocalDateTime fcreate) {
    this.fcreate = fcreate;
  }

  public String getUcreate() {
    return ucreate;
  }

  public void setUcreate(String ucreate) {
    this.ucreate = ucreate;
  }

}
