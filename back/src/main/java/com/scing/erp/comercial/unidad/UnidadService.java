package com.scing.erp.comercial.unidad;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.comercial.cliente.Cliente;
import com.scing.erp.comercial.cliente.ClienteRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class UnidadService {

  private UnidadRepository unidadRepository;
  private ClienteRepository clienteRepository;
  private ModelMapper modelMapper;

  @Autowired
  public UnidadService(UnidadRepository unidadRepository, ClienteRepository clienteRepository,
      ModelMapper modelMapper) {
    this.unidadRepository = unidadRepository;
    this.clienteRepository = clienteRepository;
    this.modelMapper = modelMapper;
  }

  public List<UnidadDTO> listUnidadByIdcliente(Long idcliente) {

    if (clienteRepository.findById(idcliente).isPresent()) {

      Cliente cliente = clienteRepository.findById(idcliente).orElse(null);

      List<Unidad> listUnidad = this.unidadRepository.findAllByClienteOrderByEliminadoAsc(cliente);

      List<UnidadDTO> listUnidadDTO = listUnidad.stream().map(unidad -> {

        UnidadDTO unidadDTO = new UnidadDTO();
        unidadDTO.setIdunidad(unidad.getIdunidad());
        unidadDTO.setNombre(unidad.getNombre());
        unidadDTO.setDescripcion(unidad.getDescripcion());
        unidadDTO.setEliminado(unidad.isEliminado());
        unidadDTO.setIdcliente(unidad.getCliente().getIdcliente());
        return unidadDTO;

      }).collect(Collectors.toList());

      return listUnidadDTO;

    } else {

      return null;
    }
  }

  public List<UnidadSelectDTO> listSelectUnidad(Long idcliente) {

    Cliente cliente = clienteRepository.findById(idcliente).orElse(null);

    if (cliente != null) {

      List<Unidad> listUnidad = this.unidadRepository.findAllByClienteAndEliminadoOrderByNombreAsc(cliente, false);

      List<UnidadSelectDTO> listUnidadSelectDTO = listUnidad.stream().map(unidad -> {

        UnidadSelectDTO unidadSelectDTO = new UnidadSelectDTO();
        unidadSelectDTO.setIdunidad(unidad.getIdunidad());
        unidadSelectDTO.setNombre(unidad.getNombre());
        return unidadSelectDTO;

      }).collect(Collectors.toList());

      return listUnidadSelectDTO;
    } else {
      return null;
    }
  }

  @Transactional
  public ResponseMensaje createUnidad(UnidadDTO unidadDTO) {

    Cliente cliente = clienteRepository.findById(unidadDTO.getIdcliente()).orElse(null);

    if (!unidadRepository.findByNombre(unidadDTO.getNombre()).isPresent()) {

      Unidad unidad = new Unidad();
      unidad.setNombre(unidadDTO.getNombre());
      unidad.setDescripcion(unidadDTO.getDescripcion());
      unidad.setEliminado(false);
      unidad.setCliente(cliente);
      unidad.setUcreate("SuperAdmin");

      unidad.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      unidadRepository.save(unidad);
      return new ResponseMensaje(200, "Unidad registrada");

    } else {

      return new ResponseMensaje(409, "Unidad con nombre, " + unidadDTO.getNombre() + " ya existe");
    }
  }

  public UnidadDTO getUnidadByIdunidad(Long idunidad) {
    Unidad unidad = unidadRepository.findById(idunidad).orElse(null);
    return modelMapper.map(unidad, UnidadDTO.class);
  }

  @Transactional
  public ResponseMensaje updateUnidad(UnidadDTO unidadDTO) {

    /*
     * Cliente cliente =
     * clienteRepository.findById(unidadDTO.getIdcliente()).orElse(null);
     */

    if (unidadRepository.findById(unidadDTO.getIdunidad()).isPresent()) {

      Unidad unidad = unidadRepository.findById(unidadDTO.getIdunidad()).orElse(null);

      unidad.setNombre(unidadDTO.getNombre());
      unidad.setDescripcion(unidadDTO.getDescripcion());
      unidad.setEliminado(false);
      /* unidad.setCliente(cliente); */
      unidad.setUupdate("SuperAdmin");
      /*
       * unidad.setUupdate(UserContextHolder.getUser().getNombres() +
       * UserContextHolder.getUser().getApellidos());
       */
      unidadRepository.save(unidad);
      return new ResponseMensaje(200, "Unidad actualizada");

    } else {

      return new ResponseMensaje(404, "Unidad , " + unidadDTO.getIdunidad() + " no encontrada");
    }
  }

  @Transactional
  public ResponseMensaje deleteUnidad(Long idunidad) {

    if (unidadRepository.findById(idunidad).isPresent()) {

      Unidad unidad = unidadRepository.findById(idunidad).orElse(null);

      String msj = "Unidad" + unidad.getNombre() + ", ha sido habilitado";

      if (unidad.isEliminado()) {
        unidad.setEliminado(false);
      } else {
        unidad.setEliminado(true);
        msj = "Unidad " + unidad.getNombre() + ", ha sido inhabilitado";
      }
      unidad.setUupdate("SuperAdmin");
      /*
       * unidad.setUupdate(UserContextHolder.getUser().getNombres() +
       * UserContextHolder.getUser().getApellidos());
       */
      unidadRepository.save(unidad);
      return new ResponseMensaje(200, msj);

    } else {

      return new ResponseMensaje(404, "Unidad no encontrada");
    }
  }
}
