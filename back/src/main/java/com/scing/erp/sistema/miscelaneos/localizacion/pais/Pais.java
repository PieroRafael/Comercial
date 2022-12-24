package com.scing.erp.sistema.miscelaneos.localizacion.pais;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.scing.erp.sistema.miscelaneos.localizacion.departamento.Departamento;

@Entity
@Table(name = "pais")
public class Pais implements Serializable {

	private static final long serialVersionUID = -4214325494311300101L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpais;

	@Length(max = 30)
	@Column(name = "nombre", nullable = false)
	@NotEmpty(message = "Por favor, ingrese un pais.")
	private String nombre;

	@Column(name = "eliminado", nullable = false)
	private boolean eliminado;

	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
	private List<Departamento> departamentos;

	public Pais() {
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Long getIdpais() {
		return idpais;
	}

	public void setIdpais(Long idpais) {
		this.idpais = idpais;
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
