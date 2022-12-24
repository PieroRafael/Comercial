package com.scing.erp.comercial.cliente;

import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public class ClienteSelectDTO {

  @NonNull
  private Long idcliente;

  @NonNull
  @NotEmpty
  private String razonsocial;

  public ClienteSelectDTO() {
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
