package com.scing.erp.sistema.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.scing.erp.sistema.rol.Rol;
import com.scing.erp.sistema.usuario.Usuario;
import com.scing.erp.sistema.usuario.UsuarioService;
import java.util.Collection;
import java.util.ArrayList;

@Service
public class BundleUserDetailsService implements UserDetailsService {

  private UsuarioService usuarioService;

  @Autowired
  public BundleUserDetailsService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    Usuario usuario = usuarioService.getUsuarioByCorreo(email);
    return new BundleUserDetails(usuario, getAuthorities(usuario.getRoles()));
  }

  private List<GrantedAuthority> getAuthorities(Collection<Rol> roles) {
    return getGrantedAuthorities(roles);
  }

  private List<GrantedAuthority> getGrantedAuthorities(Collection<Rol> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Rol rol : roles) {
      authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
    }
    return authorities;
  }

  public static class BundleUserDetails implements UserDetails {

    private static final long serialVersionUID = -3542337090559589236L;

    private Usuario usuario;
    private List<GrantedAuthority> authorities;

    BundleUserDetails(Usuario usuario, List<GrantedAuthority> authorities) {
      this.usuario = usuario;
      this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
    }

    @Override
    public String getPassword() {
      return usuario.getPasswordHash();
    }

    @Override
    public String getUsername() {
      return usuario.getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    public Usuario getUsuario() {
      return usuario;
    }
  }
}
