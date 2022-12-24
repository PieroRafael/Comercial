package com.scing.erp.comercial.documentospc;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/documentospc")
public class DocumentospcController {

  private DocumentospcService documentospcService;

  public DocumentospcController(DocumentospcService documentospcService) {
    this.documentospcService = documentospcService;
  }

  @PreAuthorize("hasAuthority('read-documentospc')")
  @PostMapping("")
  public ResponseEntity<List<DocumentospcDTO>> listDocumentospc(@RequestParam Long idseguimientospc,
      @RequestParam String rutabase) {
    return ok(documentospcService.listDocumentospc(idseguimientospc, rutabase));
  }
}
