package com.scing.erp.comercial.ot;

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
@Table(name = "ot")
public class Ot implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idot;

  @Length(max = 7)
  @Column(name = "codigo", nullable = false, unique = true)
  @NotEmpty(message = "Por favor , ingrese el código.")
  private String codigo;

  @Column(name = "tipoproyecto", nullable = false)
  private boolean tipoproyecto;

  /* Para Ot Adicional */
  @Column(name = "otprincipal", nullable = true)
  private Long otprincipal;

  @ManyToOne
  @JoinColumn(name = "idspc", nullable = false)
  private Spc spc;

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
  @NotEmpty(message = "Por favor, ingrese un usuario para la creación de la OT.")
  private String ucreate;

  @Length(max = 100)
  @Column(name = "uupdate", nullable = true)
  private String uupdate;

  public Ot() {
  }

  public Long getIdot() {
    return idot;
  }

  public void setIdot(Long idot) {
    this.idot = idot;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public boolean isTipoproyecto() {
    return tipoproyecto;
  }

  public void setTipoproyecto(boolean tipoproyecto) {
    this.tipoproyecto = tipoproyecto;
  }

  public Spc getSpc() {
    return spc;
  }

  public void setSpc(Spc spc) {
    this.spc = spc;
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

  public Long getOtprincipal() {
    return otprincipal;
  }

  public void setOtprincipal(Long otprincipal) {
    this.otprincipal = otprincipal;
  }

}
