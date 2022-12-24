package com.scing.erp.comercial.spc;

import java.sql.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import com.scing.erp.comercial.contacto.ContactoDTO;
import org.springframework.lang.NonNull;

public class SpcDTO {

  private Long idspc;

  private String codigo;
  private String proyecto;

  @NonNull
  @NotEmpty
  private String vendedor;

  @NonNull
  @NotEmpty
  private String tipo;

  private String fcreate;
  private String razonsocial;
  private Long idcliente;
  private boolean eliminado;
  private Date fechaenviodeconsulta;
  private Date fechaabsolucion;
  private Date fechaentrega;
  private Date fechareunion;
  private Date fechavisitatecnica;
  private String ubicacion;

  /* Para Detalles Contacto (Arrays) */
  private List<ContactoDTO> consulta;
  private List<ContactoDTO> tecnica;
  private List<ContactoDTO> visita;

  public SpcDTO() {
  }

  public Long getIdspc() {
    return idspc;
  }

  public void setIdspc(Long idspc) {
    this.idspc = idspc;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getProyecto() {
    return proyecto;
  }

  public void setProyecto(String proyecto) {
    this.proyecto = proyecto;
  }

  public String getVendedor() {
    return vendedor;
  }

  public void setVendedor(String vendedor) {
    this.vendedor = vendedor;
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

  public Long getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(Long idcliente) {
    this.idcliente = idcliente;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public List<ContactoDTO> getConsulta() {
    return consulta;
  }

  public void setConsulta(List<ContactoDTO> consulta) {
    this.consulta = consulta;
  }

  public List<ContactoDTO> getTecnica() {
    return tecnica;
  }

  public void setTecnica(List<ContactoDTO> tecnica) {
    this.tecnica = tecnica;
  }

  public List<ContactoDTO> getVisita() {
    return visita;
  }

  public void setVisita(List<ContactoDTO> visita) {
    this.visita = visita;
  }

  public String getFcreate() {
    return fcreate;
  }

  public void setFcreate(String fcreate) {
    this.fcreate = fcreate;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public Date getFechaenviodeconsulta() {
    return fechaenviodeconsulta;
  }

  public void setFechaenviodeconsulta(Date fechaenviodeconsulta) {
    this.fechaenviodeconsulta = fechaenviodeconsulta;
  }

  public Date getFechaabsolucion() {
    return fechaabsolucion;
  }

  public void setFechaabsolucion(Date fechaabsolucion) {
    this.fechaabsolucion = fechaabsolucion;
  }

  public Date getFechaentrega() {
    return fechaentrega;
  }

  public void setFechaentrega(Date fechaentrega) {
    this.fechaentrega = fechaentrega;
  }

  public Date getFechareunion() {
    return fechareunion;
  }

  public void setFechareunion(Date fechareunion) {
    this.fechareunion = fechareunion;
  }

  public Date getFechavisitatecnica() {
    return fechavisitatecnica;
  }

  public void setFechavisitatecnica(Date fechavisitatecnica) {
    this.fechavisitatecnica = fechavisitatecnica;
  }

}
