package com.scing.erp.comercial.ot;

import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public class OtSelectDTO {

  @NonNull
  private Long idot;

  @NonNull
  @NotEmpty
  private String codigo;

  public OtSelectDTO() {
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

}
