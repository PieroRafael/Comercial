package com.scing.erp.sistema.rol;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

  private RolRepository rolRepository;

  @Autowired
  public RolService(RolRepository roleRepository) {
    this.rolRepository = roleRepository;
  }

  public List<RolDTO> getAll() {

    List<Rol> listRol = this.rolRepository.findAll();

    List<RolDTO> listRolDTO = listRol.stream().map(rol -> {

      RolDTO rolDTO = new RolDTO();
      rolDTO.setEtiqueta(rol.getEtiqueta());

      return rolDTO;
    }).collect(Collectors.toList());

    return listRolDTO;
  }
}
