package com.scing.erp.comercial.cliente;

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
@RequestMapping("/cliente")
public class ClienteController {

  private ClienteService clienteService;

  @Autowired
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @PreAuthorize("hasAuthority('read-cliente')")
  @GetMapping("")
  public ResponseEntity<List<ClienteDTO>> listCliente() {
    return ok(clienteService.listCliente());
  }

  @PreAuthorize("hasAuthority('create-cliente')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    return ok(clienteService.createCliente(clienteDTO));
  }

  @PreAuthorize("hasAuthority('update-cliente')")
  @GetMapping("/{idcliente}")
  public ResponseEntity<ClienteDTO> getClienteByIdCliente(@PathVariable Long idcliente) {
    return ok(clienteService.getClienteByIdcliente(idcliente));
  }

  @PreAuthorize("hasAuthority('read-cliente')")
  @GetMapping("/select")
  public ResponseEntity<List<ClienteSelectDTO>> listSelectCliente() {
    return ok(clienteService.listSelectCliente());
  }

  @PreAuthorize("hasAuthority('update-cliente')")
  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    return ok(clienteService.updateCliente(clienteDTO));
  }

  @PreAuthorize("hasAuthority('delete-cliente')")
  @PutMapping("/{idcliente}")
  public ResponseEntity<ResponseMensaje> deleteCliente(@PathVariable Long idcliente) {
    return ok(clienteService.deleteCliente(idcliente));
  }
}
