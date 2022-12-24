package com.scing.erp.sistema.miscelaneos.localizacion.distrito;

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
import com.scing.erp.sistema.miscelaneos.localizacion.provincia.Provincia;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "distrito")
public class Distrito implements Serializable {

	private static final long serialVersionUID = -4214325494311300101L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iddistrito;

	@Length(max = 50)
	@Column(name = "nombre", nullable = false)
	@NotEmpty(message = "Por favor, ingrese una provincia.")
	private String nombre;

	@Column(name = "eliminado", nullable = false)
	private boolean eliminado;

	@ManyToOne
	@JoinColumn(name = "idprovincia", nullable = false)
	private Provincia provincia;

	public Distrito() {
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Long getIddistrito() {
		return iddistrito;
	}

	public void setIddistrito(Long iddistrito) {
		this.iddistrito = iddistrito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

}
