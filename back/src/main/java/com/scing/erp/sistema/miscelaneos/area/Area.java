package com.scing.erp.sistema.miscelaneos.area;

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
@Table(name = "area")
public class Area implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idarea;

	@Length(max = 30)
	@Column(name = "nombre", nullable = false, unique = true)
	@NotEmpty(message = "Por favor, ingrese el nombre del área")
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

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
	@NotEmpty(message = "Por favor, ingrese un usuario para la creación del area")
	private String ucreate;

	@Length(max = 100)
	@Column(name = "uupdate", nullable = true)
	private String uupdate;

  public Area() {
  }

  public Long getIdarea() {
    return idarea;
  }

  public void setIdarea(Long idarea) {
    this.idarea = idarea;
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
}
