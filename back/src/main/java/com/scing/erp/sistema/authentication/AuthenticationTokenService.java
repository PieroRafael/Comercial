package com.scing.erp.sistema.authentication;

import com.scing.erp.sistema.authentication.exception.TokenValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

  private Properties properties;

  @Autowired
  private AuthenticationTokenService(Properties properties) {
    this.properties = properties;
  }

  public AuthenticationToken createToken(String token) throws TokenValidationException {
    return new AuthenticationToken(token, properties.getAccessTokenSecretKey());
  }
}
