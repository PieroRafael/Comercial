package com.scing.erp.sistema.miscelaneos.localizacion.pais;

public class PaisDTO {

	private Long idpais;
	private String nombre;
	private boolean eliminado;
	private Long iddepartamento;

	public PaisDTO() {
	}

	public PaisDTO(Long idpais) {
		this.idpais = idpais;
	}

	public Long getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(Long iddepartamento) {
		this.iddepartamento = iddepartamento;
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
