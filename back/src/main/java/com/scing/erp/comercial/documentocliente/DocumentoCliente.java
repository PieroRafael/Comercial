package com.scing.erp.comercial.documentocliente;

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
@Table(name = "documentocliente")
public class Documentocliente implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iddocumentocliente;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "url", nullable = false)
  private String url;

  @Length(max = 200)
  @Column(name = "descripcion", nullable = false)
  @NotEmpty(message = "Por favor , ingrese la descripción.")
  private String descripcion;

  @Column(name = "eliminado", nullable = false)
  private boolean eliminado;

  @ManyToOne
  @JoinColumn(name = "idcliente", nullable = false)
  private Cliente cliente;

  @CreationTimestamp
  @Column(name = "fcreate")
  private LocalDateTime fcreate;

  @UpdateTimestamp
  @Column(name = "fupdate", nullable = true)
  private LocalDateTime fupdate;

  @Length(max = 100)
  @Column(name = "ucreate", nullable = false)
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación del documento.")
  private String ucreate;

  @Length(max = 100)
  @Column(name = "uupdate", nullable = true)
  private String uupdate;

  public Documentocliente() {
  }

  public Long getIddocumentocliente() {
    return iddocumentocliente;
  }

  public void setIddocumentocliente(Long iddocumentocliente) {
    this.iddocumentocliente = iddocumentocliente;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
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
