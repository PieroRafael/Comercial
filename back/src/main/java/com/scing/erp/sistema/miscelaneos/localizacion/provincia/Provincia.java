package com.scing.erp.sistema.miscelaneos.localizacion.provincia;

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
import org.hibernate.validator.constraints.Length;
import com.scing.erp.sistema.miscelaneos.localizacion.departamento.Departamento;

@Entity
@Table(name = "provincia")
public class Provincia implements Serializable {

	private static final long serialVersionUID = -4214325494311300101L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idprovincia;

	@Length(max = 50)
	@Column(name = "nombre", nullable = false)
	@NotEmpty(message = "Por favor, ingrese un provincia.")
	private String nombre;

	@Column(name = "eliminado", nullable = false)
	private boolean eliminado;

	@ManyToOne
	@JoinColumn(name = "iddepartamento", nullable = false)
	private Departamento departamento;

	public Provincia() {
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Long getIdprovincia() {
		return idprovincia;
	}

	public void setIdprovincia(Long idprovincia) {
		this.idprovincia = idprovincia;
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
