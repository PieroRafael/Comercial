package com.scing.erp.sistema.miscelaneos.localizacion.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/pais")
public class PaisController {

  private PaisService paisService;

  @Autowired
  public PaisController(PaisService paisService) {
    this.paisService = paisService;
  }

  @GetMapping("")
  public ResponseEntity<List<PaisDTO>> listPais() {
    return ok(paisService.listPais());
  }

  @GetMapping("/{idpais}")
  public ResponseEntity<PaisDTO> getPaisByIdpais(@PathVariable Long idpais) {
    return ok(paisService.getPaisByIdpais(idpais));
  }

}
