package com.scing.erp.comercial.seguimientospc;

import static org.springframework.http.ResponseEntity.ok;
import java.util.List;
import javax.validation.Valid;
import com.scing.erp.sistema.entity.ResponseMensaje;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/seguimientospc")
public class SeguimientospcController {

  private SeguimientospcService seguimientospcService;

  public SeguimientospcController(SeguimientospcService seguimientospcService) {
    this.seguimientospcService = seguimientospcService;
  }

  @GetMapping("spc/{idspc}")
  public ResponseEntity<List<SeguimientospcDTO>> listSeguimientospc(@PathVariable Long idspc) {
    return ok(seguimientospcService.listSeguimientospc(idspc));
  }

  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createSeguimientospc(@RequestParam MultipartFile[] listFile,
      @RequestParam List<String> listFolder, @RequestParam Long idspc, @RequestParam String observacion,
      @RequestParam String descripcion, @RequestParam String formarecepcion) {
    return ok(seguimientospcService.createSeguimientospc(listFile, listFolder, idspc, observacion, descripcion,
        formarecepcion));
  }

  @GetMapping("/{idseguimientospc}")
  public ResponseEntity<SeguimientospcDTO> getSeguimientospcByIdseguimientospc(@PathVariable Long idseguimientospc) {
    return ok(seguimientospcService.getSeguimientospcByIdseguimientospc(idseguimientospc));
  }

  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateSeguimientospc(@Valid @RequestBody SeguimientospcDTO seguimientospcDTO) {
    return ok(seguimientospcService.updateSeguimientospc(seguimientospcDTO));
  }

}
