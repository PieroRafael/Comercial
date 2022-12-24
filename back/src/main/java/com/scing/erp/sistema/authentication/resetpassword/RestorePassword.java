package com.scing.erp.sistema.authentication.resetpassword;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import com.scing.erp.sistema.usuario.Usuario;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.time.LocalDateTime;

@Entity
public class RestorePassword {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String token;

  @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "idusuario")
  private Usuario usuario;

  @Column(nullable = false)
  private LocalDateTime expiresIn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public LocalDateTime getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(LocalDateTime expiresIn) {
    this.expiresIn = expiresIn;
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(this.expiresIn);
  }
}
