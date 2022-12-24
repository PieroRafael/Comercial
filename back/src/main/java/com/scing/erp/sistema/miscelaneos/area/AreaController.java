package com.scing.erp.sistema.miscelaneos.area;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/area")
public class AreaController {

  private AreaService areaService;

  @Autowired
  public AreaController(AreaService areaService) {
    this.areaService = areaService;
  }

  @PreAuthorize("hasAuthority('read-area')")
  @GetMapping("")
  public ResponseEntity<List<AreaDTO>> listArea() {
    return ok(areaService.listArea());
  }

  @GetMapping("/select")
  public ResponseEntity<List<AreaDTO>> listSelectArea() {
    return ok(areaService.listSelectArea());
  }
}
