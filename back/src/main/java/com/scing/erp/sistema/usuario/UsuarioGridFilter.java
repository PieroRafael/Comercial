package com.scing.erp.sistema.usuario;

public class UsuarioGridFilter extends BaseFilter {

  private String filterBynombres;
  private String filterByapellidos;
  private String filterBycorreo;
  private String filterBynick;

  public String getFilterBynombres() {
    return filterBynombres;
  }

  public void setFilterBynombres(String filterBynombres) {
    this.filterBynombres = filterBynombres;
  }

  public String getFilterByapellidos() {
    return filterByapellidos;
  }

  public void setFilterByapellidos(String filterByapellidos) {
    this.filterByapellidos = filterByapellidos;
  }

  public String getFilterBycorreo() {
    return filterBycorreo;
  }

  public void setFilterBycorreo(String filterBycorreo) {
    this.filterBycorreo = filterBycorreo;
  }

  public String getFilterBynick() {
    return filterBynick;
  }

  public void setFilterBynick(String filterBynick) {
    this.filterBynick = filterBynick;
  }
}
