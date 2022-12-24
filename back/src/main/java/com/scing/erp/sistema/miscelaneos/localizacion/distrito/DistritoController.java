package com.scing.erp.sistema.miscelaneos.localizacion.distrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/distrito")
public class DistritoController {

  private DistritoService distritoService;

  @Autowired
  public DistritoController(DistritoService distritoService) {
    this.distritoService = distritoService;
  }

  @GetMapping("/{idprovincia}")
  public ResponseEntity<List<DistritoDTO>> listDistrito(@PathVariable Long idprovincia) {
    return ok(distritoService.listDistrito(idprovincia));
  }
}
