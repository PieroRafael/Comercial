package com.scing.erp.comercial.documentoot;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

public class DocumentootDTO {

  private Long iddocumentoot;

  @NonNull
  @NotEmpty
  private String descripcion;

  private List<String> listArea;
  private String nombre;
  private String url;
  private MultipartFile file;
  private LocalDateTime fcreate;
  private String ucreate;
  private boolean eliminado;
  private Long idot;

  public DocumentootDTO() {
  }

  public List<String> getListArea() {
    return listArea;
  }

  public void setListArea(List<String> listArea) {
    this.listArea = listArea;
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

  public String getUcreate() {
    return ucreate;
  }

  public void setUcreate(String ucreate) {
    this.ucreate = ucreate;
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

  public Long getIdot() {
    return idot;
  }

  public void setIdot(Long idot) {
    this.idot = idot;
  }
}
