package com.scing.erp.comercial.documentospc;

public class DocumentospcDTO {

  private Long iddocumentospc;
  private boolean tipo;
  private String nombre;
  private String url;

  public DocumentospcDTO() {
  }

  public Long getIddocumentospc() {
    return iddocumentospc;
  }

  public void setIddocumentospc(Long iddocumentospc) {
    this.iddocumentospc = iddocumentospc;
  }

  public boolean isTipo() {
    return tipo;
  }

  public void setTipo(boolean tipo) {
    this.tipo = tipo;
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
}
