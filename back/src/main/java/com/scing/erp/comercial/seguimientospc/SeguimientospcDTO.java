package com.scing.erp.comercial.seguimientospc;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public class SeguimientospcDTO {

  private Long idseguimientospc;

  @NonNull
  @NotEmpty
  private String formarecepcion;

  @NonNull
  @NotEmpty
  private String descripcion;

  @NonNull
  @NotEmpty
  private String observacion;

  private Long idspc;
  private LocalDateTime fcreate;

  public SeguimientospcDTO() {
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

  public Long getIdspc() {
    return idspc;
  }

  public void setIdspc(Long idspc) {
    this.idspc = idspc;
  }

  public LocalDateTime getFcreate() {
    return fcreate;
  }

  public void setFcreate(LocalDateTime fcreate) {
    this.fcreate = fcreate;
  }
}
