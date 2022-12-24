package com.scing.erp.comercial.documentocliente;

import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;

public class DocumentoclienteDTO {

  private Long iddocumentocliente;
  private String nombre;
  private String descripcion;
  private String url;
  private boolean eliminado;
  private MultipartFile file;
  private LocalDateTime fcreate;
  private String ucreate;

  public DocumentoclienteDTO() {
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

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
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
