package com.scing.erp.comercial.spc;

public class SpcSelectDTO {

  private Long idspc;
  private String proyecto;
  private String codigo;
  private String ubicacion;
  private Long idcliente;
  private String razonsocial;

  public SpcSelectDTO() {
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

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public Long getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(Long idcliente) {
    this.idcliente = idcliente;
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

}
