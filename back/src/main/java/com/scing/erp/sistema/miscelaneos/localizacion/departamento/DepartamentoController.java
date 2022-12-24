package com.scing.erp.sistema.miscelaneos.localizacion.departamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

  private DepartamentoService departamentoService;

  @Autowired
  public DepartamentoController(DepartamentoService departamentoService) {
    this.departamentoService = departamentoService;
  }

  @GetMapping("")
  public ResponseEntity<List<DepartamentoDTO>> getAll() {
    return ok(departamentoService.getAll());
  }

  @GetMapping("/{idpais}")
  public ResponseEntity<List<DepartamentoDTO>> listDepartamento(@PathVariable Long idpais) {
    return ok(departamentoService.listDepartamento(idpais));
  }
}
