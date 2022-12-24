package com.scing.erp.sistema.rol;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/rol")
public class RolController {

  private RolService rolService;

  @Autowired
  public RolController(RolService rolService) {
    this.rolService = rolService;
  }

  @PreAuthorize("hasAuthority('create-usuario')")
  @GetMapping("")
  public ResponseEntity<List<RolDTO>> getAll() {
    return ok(rolService.getAll());
  }
}
