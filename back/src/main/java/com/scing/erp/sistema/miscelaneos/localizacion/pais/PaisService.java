package com.scing.erp.sistema.miscelaneos.localizacion.pais;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

  private PaisRepository paisRepository;
  private ModelMapper modelMapper;

  @Autowired
  public PaisService(PaisRepository paisRepository, ModelMapper modelMapper) {
    this.paisRepository = paisRepository;
    this.modelMapper = modelMapper;
  }

  public List<PaisDTO> listPais() {

    List<Pais> listpais = this.paisRepository.findAllByOrderByEliminadoAsc();

    List<PaisDTO> listpaisDTO = listpais.stream().map(pais -> {

      PaisDTO paisDTO = new PaisDTO();
      paisDTO.setIdpais(pais.getIdpais());
      paisDTO.setNombre(pais.getNombre());
      paisDTO.setEliminado(pais.isEliminado());

      return paisDTO;
    }).collect(Collectors.toList());

    return listpaisDTO;
  }

  public PaisDTO getPaisByIdpais(Long idpais) {
    Pais pais = paisRepository.findById(idpais).orElse(null);
    return modelMapper.map(pais, PaisDTO.class);
  }
}
