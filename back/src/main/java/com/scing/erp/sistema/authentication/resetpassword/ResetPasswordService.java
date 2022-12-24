package com.scing.erp.sistema.authentication.resetpassword;

import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.ChangePasswordRequest;
import com.scing.erp.sistema.usuario.UserContextHolder;
import com.scing.erp.sistema.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

  private UsuarioService usuarioService;

  @Autowired
  public ResetPasswordService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  public ResponseMensaje resetPassword(ResetPasswordDTO resetPasswordDTO) {
    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
    changePasswordRequest.setUsuario(UserContextHolder.getUser());
    changePasswordRequest.setPassword(resetPasswordDTO.getPassword());
    return usuarioService.changePassword(changePasswordRequest);
  }
}
