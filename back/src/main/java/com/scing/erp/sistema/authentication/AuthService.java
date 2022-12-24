package com.scing.erp.sistema.authentication;

import com.scing.erp.sistema.authentication.exception.InvalidTokenHttpException;
import com.scing.erp.sistema.authentication.exception.UsuarioNotFoundHttpException;
import com.scing.erp.sistema.usuario.Usuario;
import com.scing.erp.sistema.usuario.UsuarioService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private UsuarioService usuarioService;
  private AuthenticationManager authenticationManager;
  private TokenService tokenService;

  @Autowired
  public AuthService(UsuarioService usuarioService, AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.usuarioService = usuarioService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  Tokens login(LoginDTO loginDTO) throws UsuarioNotFoundHttpException {
    try {
      Authentication authentication = createAuthentication(loginDTO);
      BundleUserDetailsService.BundleUserDetails userDetails = (BundleUserDetailsService.BundleUserDetails) authenticationManager
          .authenticate(authentication).getPrincipal();
      Usuario usuario = userDetails.getUsuario();

      if (!usuario.isEliminado()) {
        return createToken(usuario);
      } else {
        throw new UsuarioNotFoundHttpException("Usuario inactivo", HttpStatus.FORBIDDEN);
      }
    } catch (AuthenticationException exception) {

      Usuario usuario = usuarioService.getUsuarioByCorreo(loginDTO.getEmail());
      if (usuario == null) {
        throw new UsuarioNotFoundHttpException("Usuario con correo: " + loginDTO.getEmail() + " no existe.",
            HttpStatus.FORBIDDEN);
      }

      throw new UsuarioNotFoundHttpException("La combinación usuario/contraseña es incorrecta", HttpStatus.FORBIDDEN);
    }
  }

  Tokens refreshToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenHttpException {
    try {
      String correo = tokenService.getEmailFromRefreshToken(refreshTokenDTO.getTokens().getRefreshToken());
      Usuario usuario = usuarioService.getUsuarioByCorreo(correo);
      return createToken(usuario);
    } catch (JwtException e) {
      throw new InvalidTokenHttpException();
    }
  }

  private Authentication createAuthentication(LoginDTO loginDTO) {
    return new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
  }

  private Tokens createToken(Usuario usuario) {
    return tokenService.createToken(usuario);
  }

}
