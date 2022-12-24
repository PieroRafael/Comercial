package com.scing.erp.comercial.ot;

import java.util.List;
import javax.validation.Valid;
import com.scing.erp.sistema.entity.ResponseMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/ot")
public class OtController {

  private OtService otService;

  @Autowired
  public OtController(OtService otService) {
    this.otService = otService;
  }

  @PreAuthorize("hasAuthority('read-ot')")
  @GetMapping("")
  public ResponseEntity<List<OtDTO>> listOt() {
    return ok(otService.listOt());
  }

  @PreAuthorize("hasAuthority('read-ot')")
  @GetMapping("/adicional/{otprincipal}")
  public ResponseEntity<List<OtDTO>> listOtByOtprincipal(@PathVariable Long otprincipal) {
    return ok(otService.listOtByOtprincipal(otprincipal));
  }

  @PreAuthorize("hasAuthority('read-ot')")
  @GetMapping("/select")
  public ResponseEntity<List<OtSelectDTO>> listSelectOt() {
    return ok(otService.listSelectOt());
  }

  @PreAuthorize("hasAuthority('create-ot')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createOt(@RequestParam("listFile") MultipartFile[] listFile,
      @RequestParam Long idspc, @RequestParam String codigo, @RequestParam Boolean tipoproyecto,
      @RequestParam Long otprincipal) {
    return ok(otService.createOt(idspc, codigo, tipoproyecto, otprincipal, listFile));
  }

  @PreAuthorize("hasAuthority('update-ot')")
  @GetMapping("/{idot}")
  public ResponseEntity<OtDTO> getOtByIdot(@PathVariable Long idot) {
    return ok(otService.getOtByIdot(idot));
  }

  @PreAuthorize("hasAuthority('update-ot')")
  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateOt(@Valid @RequestBody OtDTO otDTO) {
    return ok(otService.updateOt(otDTO));
  }

  @PreAuthorize("hasAuthority('delete-ot')")
  @PutMapping("/{idot}")
  public ResponseEntity<ResponseMensaje> deleteOt(@PathVariable Long idot) {
    return ok(otService.deleteOt(idot));
  }
}
