package com.scing.erp.sistema.miscelaneos.banco;

public class BancoDTO {

	private Long idbanco;
	private String nombre;
	private Integer pdt;
	private boolean eliminado;

	public BancoDTO() {
	}

	public BancoDTO(Long idbanco) {
		this.idbanco = idbanco;
	}

	public Long getIdbanco() {
		return idbanco;
	}

	public void setIdbanco(Long idbanco) {
		this.idbanco = idbanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPdt() {
		return pdt;
	}

	public void setPdt(Integer pdt) {
		this.pdt = pdt;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

}
