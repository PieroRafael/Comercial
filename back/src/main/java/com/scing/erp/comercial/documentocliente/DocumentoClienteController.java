package com.scing.erp.comercial.documentocliente;

import java.io.IOException;
import java.util.List;
import com.scing.erp.sistema.entity.ResponseMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/documentocliente")
public class DocumentoclienteController {

  private DocumentoclienteService documentoClienteService;

  @Autowired
  public DocumentoclienteController(DocumentoclienteService documentoClienteService) {
    this.documentoClienteService = documentoClienteService;
  }

  @PreAuthorize("hasAuthority('read-documentocliente')")
  @GetMapping("/{idcliente}")
  public ResponseEntity<List<DocumentoclienteDTO>> listDocumentocliente(@PathVariable Long idcliente) {
    return ok(documentoClienteService.listDocumentocliente(idcliente));
  }

  @PreAuthorize("hasAuthority('create-documentocliente')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> uploadFile(@RequestParam("file") MultipartFile file,
      @RequestParam Long idcliente, @RequestParam String descripcion) throws IOException {
    return ok(documentoClienteService.createDocumentocliente(file, idcliente, descripcion));
  }

  @PreAuthorize("hasAuthority('delete-documentocliente')")
  @PutMapping("/{iddocumentocliente}")
  public ResponseEntity<ResponseMensaje> deleteDocumentocliente(@PathVariable Long iddocumentocliente) {
    return ok(documentoClienteService.deleteDocumentocliente(iddocumentocliente));
  }
}
