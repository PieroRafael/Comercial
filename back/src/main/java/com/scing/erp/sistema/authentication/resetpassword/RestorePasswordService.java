package com.scing.erp.sistema.authentication.resetpassword;

import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.ChangePasswordRequest;
import com.scing.erp.sistema.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

@Service
public class RestorePasswordService {

  private RestorePasswordTokenRepository restorePasswordTokenRepository;
  private UsuarioService usuarioService;

  @Autowired
  public RestorePasswordService(RestorePasswordTokenRepository restorePasswordTokenRepository,
      UsuarioService usuarioService) {
    this.restorePasswordTokenRepository = restorePasswordTokenRepository;
    this.usuarioService = usuarioService;
  }

  public ResponseMensaje restorePassword(RestorePasswordDTO restorePasswordDTO) {

    try {
      byte[] decodedBytes = Base64.getDecoder().decode(restorePasswordDTO.getToken());
      String decodedString = new String(decodedBytes);
      JSONObject token = new JSONObject(decodedString);

      RestorePassword restorePassword = restorePasswordTokenRepository.findByToken(token.getString("token"));

      if (Objects.isNull(restorePassword) || restorePassword.isExpired()) {
        return new ResponseMensaje(403, "La solicitud de reinicio de contraseña ha caducado");
      }

      changePassword(restorePasswordDTO, restorePassword);
      restorePasswordTokenRepository.delete(restorePassword);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return new ResponseMensaje(200, "Contraseña actualizada");
  }

  public void removeExpiredRestorePasswordTokens() {
    restorePasswordTokenRepository.deleteExpiredRestorePasswordTokens(LocalDateTime.now());
  }

  private void changePassword(RestorePasswordDTO restorePasswordDTO, RestorePassword restorePassword) {
    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
    changePasswordRequest.setUsuario(restorePassword.getUsuario());
    changePasswordRequest.setPassword(restorePasswordDTO.getNewPassword());

    usuarioService.changePassword(changePasswordRequest);
  }
}
