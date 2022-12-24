package com.scing.erp.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import com.scing.erp.sistema.entity.GridData;
import com.scing.erp.sistema.entity.ResponseMensaje;
import java.util.List;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

  private UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PreAuthorize("hasAuthority('read-usuario')")
  @GetMapping("")
  public ResponseEntity<GridData<UsuarioDTO>> getDataForGrid(UsuarioGridFilter usuarioGridFilter) {
    usuarioGridFilter = usuarioGridFilter == null ? new UsuarioGridFilter() : usuarioGridFilter;
    return ok(usuarioService.listUsuario(usuarioGridFilter));
  }

  @PreAuthorize("hasAuthority('create-spc')")
  @GetMapping("/vendedor")
  public ResponseEntity<List<UsuarioSelectDTO>> listSelectVendedor() {
    return ok(usuarioService.listSelectVendedor());
  }

  @PreAuthorize("hasAuthority('create-usuario')")
  @PostMapping("")
  public ResponseEntity<ResponseMensaje> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
    return ok(usuarioService.createUsuario(usuarioDTO));
  }

  @PreAuthorize("hasAuthority('update-usuario')")
  @GetMapping("/{idusuario}")
  public ResponseEntity<UsuarioDTO> getUsuarioByIdusuario(@PathVariable Long idusuario) {
    return ok(usuarioService.getUsuarioByIdusuario(idusuario));
  }

  @PreAuthorize("hasAuthority('update-usuario')")
  @PutMapping("")
  public ResponseEntity<ResponseMensaje> updateUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
    return ok(usuarioService.updateUsuario(usuarioDTO));
  }

  @PreAuthorize("hasAuthority('delete-usuario')")
  @PutMapping("/{idusuario}")
  public ResponseEntity<ResponseMensaje> deleteUsuario(@PathVariable Long idusuario) {
    return ok(usuarioService.deleteUsuario(idusuario));
  }

  @GetMapping("/current")
  public ResponseEntity<UsuarioDTO> getCurrentUsuario() {
    return ok(usuarioService.getCurrentUsuario());
  }
}
