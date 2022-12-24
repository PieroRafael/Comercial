package com.scing.erp.sistema.miscelaneos.area;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class AreaService {

  private AreaRepository areaRepository;
  private ModelMapper modelMapper;

  @Autowired
  public AreaService(AreaRepository areaRepository, ModelMapper modelMapper) {
    this.areaRepository = areaRepository;
    this.modelMapper = modelMapper;
  }

  public List<AreaDTO> listArea() {

    List<Area> listArea = this.areaRepository.findAllByOrderByEliminadoAscNombreAsc();

    List<AreaDTO> listAreaDTO = listArea.stream().map(area -> {

      AreaDTO areaDTO = modelMapper.map(area, AreaDTO.class);

      return areaDTO;
    }).collect(Collectors.toList());

    return listAreaDTO;
  }

  public List<AreaDTO> listSelectArea() {

    List<Area> listArea = this.areaRepository.findAllByEliminadoOrderByNombreAsc(false);

    List<AreaDTO> listAreaDTO = listArea.stream().map(area -> {

      AreaDTO areaDTO = new AreaDTO();
      areaDTO.setIdarea(area.getIdarea());
      areaDTO.setNombre(area.getNombre());

      return areaDTO;
    }).collect(Collectors.toList());

    return listAreaDTO;
  }
}
