package com.scing.erp.sistema.usuario;

import com.scing.erp.sistema.authentication.BundleUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContextHolder {

  private UserContextHolder() {
  }

  public static Usuario getUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    BundleUserDetailsService.BundleUserDetails userDetails = (BundleUserDetailsService.BundleUserDetails) principal;
    return userDetails.getUsuario();
  }
}
