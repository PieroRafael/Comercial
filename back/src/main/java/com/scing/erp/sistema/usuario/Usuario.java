package com.scing.erp.sistema.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import com.scing.erp.sistema.miscelaneos.area.Area;
import com.scing.erp.sistema.rol.Rol;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

  private static final long serialVersionUID = -4214325494311301488L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idusuario;

	@Length(max = 30)
	@Column(name = "nombres", nullable = false)
	@NotEmpty(message = "Por favor, ingrese los nombres.")
	private String nombres;

	@Length(max = 30)
	@Column(name = "apellidos", nullable = false)
	@NotEmpty(message = "Por favor, ingrese los apellidos.")
	private String apellidos;

  @Column(name = "nick", nullable = false, unique = true)
  @NotEmpty(message = "Por favor, ingrese un nick.")
  private String nick;

	@Column(name = "correo", nullable = false, unique = true)
	@NotEmpty(message = "Por favor, ingrese un correo")
	private String correo;

	@Length(max = 9)
	@Column(name = "celular", nullable = true, unique = true)
	private String celular;

	@Column(name = "password_hash", nullable = false)
	@NotEmpty(message = "Por favor, ingrese una contraseña")
	private String passwordHash;

  @Column(name = "vendedor", nullable = false)
  private boolean vendedor;

  @Column(name = "eliminado", nullable = false)
  private boolean eliminado;

  @ManyToOne
  @JoinColumn(name = "idarea", nullable = false)
  private Area area;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "usuario_rol", joinColumns = { @JoinColumn(name = "idusuario") }, inverseJoinColumns = {
      @JoinColumn(name = "idrol") })
  private Set<Rol> roles;

	@CreationTimestamp
	@Column(name = "fcreate")
	private LocalDateTime fcreate;

	@UpdateTimestamp
	@Column(name = "fupdate", nullable = true)
	private LocalDateTime fupdate;

	@Length(max = 100)
	@Column(name = "ucreate", nullable = false)
	@NotEmpty(message = "Por favor, ingrese un usuario para la creación del usuario.")
	private String ucreate;

	@Length(max = 100)
	@Column(name = "uupdate", nullable = true)
	private String uupdate;

  public Usuario() {
  }

  public Long getIdusuario() {
    return idusuario;
  }

  public void setIdusuario(Long idusuario) {
    this.idusuario = idusuario;
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

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public boolean isVendedor() {
    return vendedor;
  }

  public void setVendedor(boolean vendedor) {
    this.vendedor = vendedor;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public Set<Rol> getRoles() {
    return roles;
  }

  public void setRoles(Set<Rol> roles) {
    this.roles = roles;
  }

  public LocalDateTime getFcreate() {
    return fcreate;
  }

  public void setFcreate(LocalDateTime fcreate) {
    this.fcreate = fcreate;
  }

  public LocalDateTime getFupdate() {
    return fupdate;
  }

  public void setFupdate(LocalDateTime fupdate) {
    this.fupdate = fupdate;
  }

  public String getUcreate() {
    return ucreate;
  }

  public void setUcreate(String ucreate) {
    this.ucreate = ucreate;
  }

  public String getUupdate() {
    return uupdate;
  }

  public void setUupdate(String uupdate) {
    this.uupdate = uupdate;
  }
}
