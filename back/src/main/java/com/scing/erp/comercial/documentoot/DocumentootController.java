package com.scing.erp.comercial.documentoot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import com.scing.erp.sistema.entity.ResponseMensaje;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/documentoot")
public class DocumentootController {

  private DocumentootService documentootService;

  @Autowired
  public DocumentootController(DocumentootService documentootService) {
    this.documentootService = documentootService;
  }

  @PreAuthorize("hasAuthority('read-documentoot')")
  @GetMapping("ot/{idot}")
  public ResponseEntity<List<DocumentootDTO>> listDocumentoot(@PathVariable Long idot) {
    return ok(documentootService.listDocumentoot(idot));
  }

  @PreAuthorize("hasAuthority('create-documentoot')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createDocumentoot(@RequestParam List<String> listArea,
      @RequestParam("file") MultipartFile file, @RequestParam Long idot, @RequestParam String descripcion)
      throws IOException {
    return ok(documentootService.createDocumentoot(listArea, file, idot, descripcion));
  }

  @PreAuthorize("hasAuthority('update-documentoot')")
  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateFile(@Valid @RequestBody DocumentootDTO documentootDTO) {
    return ok(documentootService.updateDocumentoot(documentootDTO));
  }

  @PreAuthorize("hasAuthority('delete-documentoot')")
  @PutMapping("/{iddocumentoot}")
  public ResponseEntity<ResponseMensaje> deleteDocumentoot(@PathVariable Long iddocumentoot) {
    return ok(documentootService.deleteDocumentoot(iddocumentoot));
  }

  @PreAuthorize("hasAuthority('update-documentoot')")
  @GetMapping("/{iddocumentoot}")
  public ResponseEntity<DocumentootDTO> getDocumentootByIddocumentoot(@PathVariable Long iddocumentoot) {
    return ok(documentootService.getDocumentootByIddocumentoot(iddocumentoot));
  }
}
