package com.scing.erp.sistema.miscelaneos.localizacion.departamento;

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
import com.scing.erp.sistema.miscelaneos.localizacion.pais.Pais;

@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

	private static final long serialVersionUID = -4214325494311300101L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iddepartamento;

	@Length(max = 50)
	@Column(name = "nombre", nullable = false)
	@NotEmpty(message = "Por favor, ingrese un departamento.")
	private String nombre;

	@Column(name = "eliminado", nullable = false)
	private boolean eliminado;

	@ManyToOne
	@JoinColumn(name = "idpais", nullable = false)
	private Pais pais;

	public Departamento() {
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Long getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(Long iddepartamento) {
		this.iddepartamento = iddepartamento;
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
