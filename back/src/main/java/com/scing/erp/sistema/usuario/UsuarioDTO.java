package com.scing.erp.sistema.usuario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.scing.erp.sistema.miscelaneos.area.AreaDTO;
import com.scing.erp.sistema.rol.RolDTO;
import java.util.List;

public class UsuarioDTO {

	private Long idusuario;

	@NotEmpty
	@NotNull
	private String nombres;

	@NotEmpty
	@NotNull
	private String apellidos;

	@NotEmpty
	@NotNull
	private String nick;

	@NotEmpty
	@NotNull
	private String correo;

	@NotNull
	private AreaDTO area;

	private String celular;
	private Boolean eliminado;
	private Boolean vendedor;
	private List<RolDTO> roles;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String nick, String correo) {
		this.nick = nick;
		this.correo = correo;
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public AreaDTO getArea() {
		return area;
	}

	public void setArea(AreaDTO area) {
		this.area = area;
	}

	public Boolean getVendedor() {
		return vendedor;
	}

	public void setVendedor(Boolean vendedor) {
		this.vendedor = vendedor;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public List<RolDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDTO> roles) {
		this.roles = roles;
	}
}
