package com.scing.erp.sistema.miscelaneos.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scing.erp.sistema.entity.ResponseMensaje;
import java.util.List;
import javax.validation.Valid;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/banco")
public class BancoController {

  private BancoService bancoService;

  @Autowired
  public BancoController(BancoService bancoService) {
    this.bancoService = bancoService;
  }

  @GetMapping("")
  public ResponseEntity<List<BancoDTO>> listBanco() {
    return ok(bancoService.listBanco());
  }

  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createBanco(@Valid @RequestBody BancoDTO bancoDTO) {
    return ok(bancoService.createBanco(bancoDTO));
  }

  @GetMapping("/{idbanco}")
  public ResponseEntity<BancoDTO> getBancoByIdbanco(@PathVariable Long idbanco) {
    return ok(bancoService.getBancoByIdbanco(idbanco));
  }

  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateBanco(@Valid @RequestBody BancoDTO bancoDTO) {
    return ok(bancoService.updateBanco(bancoDTO));
  }

  @PutMapping("/{idbanco}")
  public ResponseEntity<ResponseMensaje> deleteBanco(@PathVariable Long idbanco) {
    return ok(bancoService.deleteBanco(idbanco));
  }
}
