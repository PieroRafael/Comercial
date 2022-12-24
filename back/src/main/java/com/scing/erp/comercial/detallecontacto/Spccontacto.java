package com.scing.erp.comercial.detallecontacto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.scing.erp.comercial.contacto.Contacto;
import com.scing.erp.comercial.spc.Spc;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "spccontacto")
public class Spccontacto implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idspccontacto;

  @ManyToOne
  @JoinColumn(name = "idcontacto", nullable = false)
  private Contacto contacto;

  @ManyToOne
  @JoinColumn(name = "idspc", nullable = false)
  private Spc spc;

  @Length(max = 30)
  @Column(name = "ocupacion", nullable = false)
  @NotEmpty(message = "Por favor, seleccione una ocupación.")
  private String ocupacion;

  public Spccontacto() {
  }

  public Long getIdspccontacto() {
    return idspccontacto;
  }

  public void setIdspccontacto(Long idspccontacto) {
    this.idspccontacto = idspccontacto;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public void setContacto(Contacto contacto) {
    this.contacto = contacto;
  }

  public Spc getSpc() {
    return spc;
  }

  public void setSpc(Spc spc) {
    this.spc = spc;
  }

  public String getOcupacion() {
    return ocupacion;
  }

  public void setOcupacion(String ocupacion) {
    this.ocupacion = ocupacion;
  }

}
