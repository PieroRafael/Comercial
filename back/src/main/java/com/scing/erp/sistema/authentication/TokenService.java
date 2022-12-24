package com.scing.erp.sistema.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import com.scing.erp.sistema.authentication.exception.AuthenticationException;
import com.scing.erp.sistema.rol.Rol;
import com.scing.erp.sistema.usuario.Usuario;
import java.util.Date;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;

@Service
public class TokenService {

  private String accessTokenSecretKey;
  private String refreshTokenSecretKey;
  private long accessTokenValidityInMilliseconds;
  private long refreshTokenValidityInMilliseconds;
  private UserDetailsService userDetailsService;
  private AuthenticationTokenService authenticationTokenService;

  @Autowired
  public TokenService(UserDetailsService userDetailsService, AuthenticationTokenService authenticationTokenService,
      Properties properties) {
    this.userDetailsService = userDetailsService;
    this.authenticationTokenService = authenticationTokenService;
    accessTokenSecretKey = properties.getAccessTokenSecretKey();
    accessTokenValidityInMilliseconds = properties.getAccessTokenValidityInMilliseconds();
    refreshTokenSecretKey = properties.getRefreshTokenSecretKey();
    refreshTokenValidityInMilliseconds = properties.getRefreshTokenValidityInMilliseconds();
  }

  public Tokens createToken(Usuario usuario) {
    Tokens token = new Tokens();
    long expiresIn = expiration(accessTokenValidityInMilliseconds);

    token.setAccessToken(createAccessToken(usuario));
    token.setRefreshToken(createRefreshToken(usuario));
    token.setExpiresIn(expiresIn);

    return token;
  }

  Authentication getAuthentication(AuthenticationToken token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(token.getEmail());
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  String getEmailFromRefreshToken(String token) throws JwtException {
    return Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(token).getBody().getSubject();
  }

  AuthenticationToken resolveToken(HttpServletRequest req) throws AuthenticationException {
    String bearer = "Bearer ";
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken == null) {
      throw new AuthenticationException("Authorization header should be present");
    }
    if (!bearerToken.startsWith(bearer)) {
      throw new AuthenticationException("Authorization header should begin with Bearer");
    }

    return authenticationTokenService.createToken(bearerToken.substring(bearer.length()));
  }

  private String createAccessToken(Usuario usuario) {
    long expiresIn = expiration(accessTokenValidityInMilliseconds);

    return createToken(usuario, expiresIn, accessTokenSecretKey);
  }

  private String createRefreshToken(Usuario usuario) {
    long expiresIn = expiration(refreshTokenValidityInMilliseconds);

    return createToken(usuario, expiresIn, refreshTokenSecretKey);
  }

  private List<String> getRoleNames(Set<Rol> roles) {
    return roles.stream().map(Rol::getNombre).collect(toList());
  }

  private String createToken(Usuario usuario, long expiresIn, String key) {
    Claims claims = Jwts.claims();

    claims.setSubject(usuario.getCorreo());
    claims.put("fullName", String.join(" ", usuario.getNombres(), usuario.getApellidos()));
    claims.put("createdAt", usuario.getFcreate());
    claims.put("role", getRoleNames(usuario.getRoles()));
    Date now = new Date();
    Date expirationDate = new Date(expiresIn);

    return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, key).compact();
  }

  private long expiration(long validity) {
    Date now = new Date();
    return now.getTime() + validity;
  }

  public String getAccessTokenSecretKey() {
    return accessTokenSecretKey;
  }
}
