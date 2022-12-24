package com.scing.erp.sistema.authentication.resetpassword;

import com.scing.erp.sistema.authentication.resetpassword.exception.CantSendEmailHttpException;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.Usuario;
import com.scing.erp.sistema.usuario.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class RequestPasswordService {

  private Logger logger = LoggerFactory.getLogger(RequestPasswordService.class);

  private UsuarioService usuarioService;
  private RestorePasswordTokenRepository restorePasswordTokenRepository;
  private JavaMailSender mailSender;

  @Autowired
  public RequestPasswordService(UsuarioService usuarioService,
      RestorePasswordTokenRepository restorePasswordTokenRepository, JavaMailSender mailSender) {
    this.usuarioService = usuarioService;
    this.restorePasswordTokenRepository = restorePasswordTokenRepository;
    this.mailSender = mailSender;
  }

  @Value("${client.url}")
  private String clientUrl;

  @Value("${client.resetPasswordTokenExpiration}")
  private Duration resetPasswordTokenExpiration;

  public ResponseMensaje requestPassword(RequestPasswordDTO requestPasswordDTO) {

    Usuario usuario = usuarioService.getUsuarioByCorreo(requestPasswordDTO.getEmail());

    if (usuario != null) {

      RestorePassword token = new RestorePassword();
      token.setToken(UUID.randomUUID().toString());
      token.setUsuario(usuario);
      token.setExpiresIn(this.calculateExpirationDate(resetPasswordTokenExpiration));

      MimeMessage message = mailSender.createMimeMessage();

      try {
        String resetPasswordUrl = createResetUrl(token);

        message.setSubject("SCING ERP");
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setTo(requestPasswordDTO.getEmail());
        helper.setText(this.msg(resetPasswordUrl), true);
        mailSender.send(message);

        logger.info("Correo de recuperación enviado");
      } catch (MessagingException e) {
        throw new CantSendEmailHttpException();
      } catch (JsonProcessingException e) {
        throw new CantSendEmailHttpException();
      }

      restorePasswordTokenRepository.save(token);

      return new ResponseMensaje(200, "Correo de recuperación enviado");
    } else {
      return new ResponseMensaje(404, "Correo no registrado");
    }
  }

  private LocalDateTime calculateExpirationDate(Duration tokenExpirationDuration) {
    return LocalDateTime.now().plusMinutes(tokenExpirationDuration.toMinutes());
  }

  private String createResetUrl(RestorePassword restorePassword) throws JsonProcessingException {

    RestorePasswordTokenDto restorePasswordTokenDto = new RestorePasswordTokenDto();
    restorePasswordTokenDto.setExpiryDate(restorePassword.getExpiresIn());
    restorePasswordTokenDto.setToken(restorePassword.getToken());

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonToken = objectMapper.writeValueAsString(restorePasswordTokenDto);

    String encodedToken = Base64.getEncoder().encodeToString(jsonToken.getBytes(UTF_8));

    return clientUrl + "/auth/restore-pass/" + encodedToken;
  }

  private String msg(String url) {
    return "<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + "<meta charset='UTF-8'>"
        + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
        + "<link href='https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap' rel='stylesheet'>"
        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" + "<title>Request password</title>"
        + "</head>" + "<body>" + "<div style='background-color: #003870; height: 120px;'></div>"
        + "<div style='background-color: rgb(255, 255, 255); height: 500px; font-size: 20px; font-family: 'Roboto', sans-serif; border: #003870;'>"
        + "<div style='padding: 5%; text-align: center;'>" + "<div>"
        + "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToZKUHGGYZ3TSrHwN82N0FFdXTzMwpZbCFScUKVkXoF2nu2ElBxJq2mFa7bEaSIgw60qE&usqp=CAU' alt='sc-ingenieria'>"
        + "</div>"
        + "Hola, para continuar con el procedimiento de restauración de contraseña, por favor presiona el botón"
        + "mostrado a continuación: <br>" + "<a href='" + url
        + "' style='text-decoration: none; color: white; font-size: 15px; margin-top:10%;'>"
        + "<div style='text-align: center; background-color: #003870; height: 42px; width: 270px; font-weight: bold; border: none; border-radius: 20px; display: inline-block; margin: 3%;'>"
        + "<h4 style='margin-block: 0px; margin:4.5%;'>REESTABLECER CONTRASEÑA</h4>" + "</div>" + "</a>" + "<br>"
        + "<p style='font-family: 'Roboto', sans-serif; color: rgb(92, 89, 89); font-size: 15px;'>Si no ha solicitado la restauración de su contraseña, por favor omitir este mensaje."
        + "</p>" + "</div>" + "</div>"
        + "<div style='background-color: #003870; height: 120px; color: rgb(255,255,255); margin-top: auto; font-size: 15px;'>"
        + "<div style='text-align: center;'>"
        + "<div style='display: inline-block; width: 100%; height: auto; margin-block: 0; margin-top: 35px;'><span>SC Ingeniería y Construcción</span></div>"
        + "<div style='display: inline-block;'><span>Lima, PE - 2021</span></div>"
        + "</div>" + "</div>" + "</body></html>";
  }
}
