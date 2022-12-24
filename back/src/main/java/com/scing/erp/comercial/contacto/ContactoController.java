package com.scing.erp.comercial.contacto;

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
import java.util.List;
import javax.validation.Valid;
import com.scing.erp.sistema.entity.ResponseMensaje;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

  private ContactoService contactoService;

  @Autowired
  public ContactoController(ContactoService contactoService) {
    this.contactoService = contactoService;
  }

  @PreAuthorize("hasAuthority('read-representante')")
  @GetMapping("")
  public ResponseEntity<List<ContactoDTO>> listContacto() {
    return ok(contactoService.listContacto());
  }

  @PreAuthorize("hasAuthority('read-representante')")
  @GetMapping("cliente/{idcliente}")
  public ResponseEntity<List<ContactoDTO>> listContactoByIdcliente(@PathVariable Long idcliente) {
    return ok(contactoService.listContactoByIdcliente(idcliente));
  }

  @PreAuthorize("hasAuthority('create-representante')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createContacto(@Valid @RequestBody ContactoDTO contactoDTO) {
    return ok(contactoService.createContacto(contactoDTO));
  }

  @PreAuthorize("hasAuthority('delete-representante')")
  @PutMapping("/{idcontacto}")
  public ResponseEntity<ResponseMensaje> deleteContacto(@PathVariable Long idcontacto) {
    return ok(contactoService.deleteContacto(idcontacto));
  }

  @PreAuthorize("hasAuthority('update-representante')")
  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateContacto(@Valid @RequestBody ContactoDTO contactoDTO) {
    return ok(contactoService.updateContacto(contactoDTO));
  }

  @PreAuthorize("hasAuthority('update-representante')")
  @GetMapping("/{idcontacto}")
  public ResponseEntity<ContactoDTO> getContactoByIdcontacto(@PathVariable Long idcontacto) {
    return ok(contactoService.getContactoByIdcontacto(idcontacto));
  }
}
