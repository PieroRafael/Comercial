package com.scing.erp.comercial.unidad;

import javax.validation.Valid;
import com.scing.erp.sistema.entity.ResponseMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/unidad")
public class UnidadController {

  private UnidadService unidadService;

  @Autowired
  public UnidadController(UnidadService unidadService) {
    this.unidadService = unidadService;
  }

  @GetMapping("cliente/{idcliente}")
  public ResponseEntity<List<UnidadDTO>> listUnidadByIdcliente(@PathVariable Long idcliente) {
    return ok(unidadService.listUnidadByIdcliente(idcliente));
  }

  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createUnidad(@Valid @RequestBody UnidadDTO unidadDTO) {
    return ok(unidadService.createUnidad(unidadDTO));
  }

  @GetMapping("/{idunidad}")
  public ResponseEntity<UnidadDTO> getUnidadByIdunidad(@PathVariable Long idunidad) {
    return ok(unidadService.getUnidadByIdunidad(idunidad));
  }

  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateUnidad(@Valid @RequestBody UnidadDTO unidadDTO) {
    return ok(unidadService.updateUnidad(unidadDTO));
  }

  @PutMapping("/{idunidad}")
  public ResponseEntity<ResponseMensaje> deleteUnidad(@PathVariable Long idunidad) {
    return ok(unidadService.deleteUnidad(idunidad));
  }

  @GetMapping("/select/{idcliente}")
  public ResponseEntity<List<UnidadSelectDTO>> listSelectUnidad(@PathVariable Long idcliente) {
    return ok(unidadService.listSelectUnidad(idcliente));
  }
}
