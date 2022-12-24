package com.scing.erp.sistema.miscelaneos.localizacion.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/provincia")
public class ProvinciaController {

  private ProvinciaService provinciaService;

  @Autowired
  public ProvinciaController(ProvinciaService provinciaService) {
    this.provinciaService = provinciaService;
  }

  @GetMapping("/{iddepartamento}")
  public ResponseEntity<List<ProvinciaDTO>> listProvincia(@PathVariable Long iddepartamento) {
    return ok(provinciaService.listProvincia(iddepartamento));
  }
}
