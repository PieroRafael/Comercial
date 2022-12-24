package com.scing.erp.comercial.documentoot;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;
import com.scing.erp.comercial.ot.Ot;
import com.scing.erp.sistema.miscelaneos.area.Area;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "documentoot")
public class Documentoot implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iddocumentoot;

  @Length(max = 200)
  @Column(name = "descripcion", nullable = false)
  @NotEmpty(message = "Por favor, ingrese una descripción.")
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "idot", nullable = false)
  private Ot ot;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "documentootarea", joinColumns = { @JoinColumn(name = "iddocumentoot") }, inverseJoinColumns = {
      @JoinColumn(name = "idarea") })
  private Set<Area> area;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "url", nullable = false)
  private String url;

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

  @Column(name = "eliminado", nullable = false)
  private boolean eliminado;

  public Documentoot() {
  }

  public Long getIddocumentoot() {
    return iddocumentoot;
  }

  public void setIddocumentoot(Long iddocumentoot) {
    this.iddocumentoot = iddocumentoot;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
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

  public Ot getOt() {
    return ot;
  }

  public void setOt(Ot ot) {
    this.ot = ot;
  }

  public Set<Area> getArea() {
    return area;
  }

  public void setArea(Set<Area> area) {
    this.area = area;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

}
