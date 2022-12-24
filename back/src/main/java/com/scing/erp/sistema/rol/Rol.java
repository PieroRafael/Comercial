package com.scing.erp.sistema.rol;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "rol")
public class Rol implements GrantedAuthority {

	private static final long serialVersionUID = 1674924613078177003L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrol", nullable = false)
	private Long idrol;

	@Column(name = "ruta", nullable = false, unique = true)
	private String ruta;

	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre;

	@Column(name = "etiqueta", nullable = false, unique = true)
	private String etiqueta;

	@Override
	public String getAuthority() {
		return nombre;
	}

	public Long getIdrol() {
		return idrol;
	}

	public void setIdrol(Long idrol) {
		this.idrol = idrol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
