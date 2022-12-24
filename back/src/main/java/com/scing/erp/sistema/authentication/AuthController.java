package com.scing.erp.sistema.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import com.scing.erp.sistema.authentication.exception.PasswordsDontMatchException;
import com.scing.erp.sistema.authentication.resetpassword.RequestPasswordDTO;
import com.scing.erp.sistema.authentication.resetpassword.RequestPasswordService;
import com.scing.erp.sistema.authentication.resetpassword.ResetPasswordDTO;
import com.scing.erp.sistema.authentication.resetpassword.ResetPasswordService;
import com.scing.erp.sistema.authentication.resetpassword.RestorePasswordDTO;
import com.scing.erp.sistema.authentication.resetpassword.RestorePasswordService;
import com.scing.erp.sistema.entity.ResponseMensaje;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private final RequestPasswordService requestPasswordService;
  private final RestorePasswordService restorePasswordService;
  private final ResetPasswordService resetPasswordService;

  @Autowired
  public AuthController(AuthService authService, RequestPasswordService requestPasswordService,
      RestorePasswordService restorePasswordService, ResetPasswordService resetPasswordService) {
    this.authService = authService;
    this.requestPasswordService = requestPasswordService;
    this.restorePasswordService = restorePasswordService;
    this.resetPasswordService = resetPasswordService;
  }

  @PostMapping("/login")
  public ResponseEntity<RefreshTokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
    Tokens tokens = authService.login(loginDTO);
    return toResponse(tokens);
  }

  @PostMapping("/restore-pass")
  public ResponseEntity<ResponseMensaje> restorePassword(@Valid @RequestBody RestorePasswordDTO restorePasswordDTO) {
    if (!restorePasswordDTO.getNewPassword().equals(restorePasswordDTO.getConfirmPassword())) {
      throw new PasswordsDontMatchException();
    }
    return ok(restorePasswordService.restorePassword(restorePasswordDTO));
  }

  @PostMapping("/request-pass")
  public ResponseEntity<ResponseMensaje> requestPassword(@Valid @RequestBody RequestPasswordDTO requestPasswordDTO) {
    return ok(requestPasswordService.requestPassword(requestPasswordDTO));
  }

  @PostMapping("/sign-out")
  public ResponseEntity<ResponseMessage> logout() {
    return ok(new ResponseMessage("Ok"));
  }

  @PostMapping("/reset-pass")
  public ResponseEntity<ResponseMensaje> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
    if (!resetPasswordDTO.getConfirmPassword().equals(resetPasswordDTO.getPassword())) {
      throw new PasswordsDontMatchException();
    }
    return ok(resetPasswordService.resetPassword(resetPasswordDTO));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<RefreshTokenDTO> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
    Tokens tokens = authService.refreshToken(refreshTokenDTO);
    return toResponse(tokens);
  }

  private ResponseEntity<RefreshTokenDTO> toResponse(Tokens tokens) {
    return ok(new RefreshTokenDTO(tokens));
  }
}
