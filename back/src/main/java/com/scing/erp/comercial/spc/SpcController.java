package com.scing.erp.comercial.spc;

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
import static org.springframework.http.ResponseEntity.ok;
import java.util.List;

@Controller
@RequestMapping("/spc")
public class SpcController {

  private SpcService spcService;

  @Autowired
  public SpcController(SpcService spcService) {
    this.spcService = spcService;
  }

  @PreAuthorize("hasAuthority('create-spc')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createSpc(@Valid @RequestBody SpcDTO spcDTO) {
    return ok(spcService.createSpc(spcDTO));
  }

  @PreAuthorize("hasAuthority('read-spc')")
  @GetMapping("")
  public ResponseEntity<List<SpcDTO>> listSpc() {
    return ok(spcService.listSpc());
  }

  @PreAuthorize("hasAuthority('delete-spc')")
  @PutMapping("/{idspc}")
  public ResponseEntity<ResponseMensaje> deleteSpc(@PathVariable Long idspc) {
    return ok(spcService.deleteSpc(idspc));
  }

  @PreAuthorize("hasAuthority('update-spc')")
  @GetMapping("/{idspc}")
  public ResponseEntity<SpcDTO> getSpcByIdspc(@PathVariable Long idspc) {
    return ok(spcService.getSpcByIdspc(idspc));
  }

  @PreAuthorize("hasAuthority('update-spc')")
  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateSpc(@Valid @RequestBody SpcDTO spcDTo) {
    return ok(spcService.updateSpc(spcDTo));
  }

  @PreAuthorize("hasAuthority('read-spc')")
  @GetMapping("/select")
  public ResponseEntity<List<SpcSelectDTO>> listSelectSpc() {
    return ok(spcService.listSelectSpc());
  }
}
