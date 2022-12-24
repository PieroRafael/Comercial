package com.scing.erp.comercial.ot;

import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public class OtDTO {

  private Long idot;

  @NonNull
  @NotEmpty
  private String codigo;
  private String fcreate;
  private boolean tipoproyecto;
  private Long otprincipal;
  private boolean eliminado;

  private Long idspc;
  private String proyecto;
  private String codigospc;

  public OtDTO() {
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

  public Long getIdspc() {
    return idspc;
  }

  public void setIdspc(Long idspc) {
    this.idspc = idspc;
  }

  public String getProyecto() {
    return proyecto;
  }

  public void setProyecto(String proyecto) {
    this.proyecto = proyecto;
  }

  public String getCodigospc() {
    return codigospc;
  }

  public void setCodigospc(String codigospc) {
    this.codigospc = codigospc;
  }

  public Long getOtprincipal() {
    return otprincipal;
  }

  public void setOtprincipal(Long otprincipal) {
    this.otprincipal = otprincipal;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public String getFcreate() {
    return fcreate;
  }

  public void setFcreate(String fcreate) {
    this.fcreate = fcreate;
  }

}
