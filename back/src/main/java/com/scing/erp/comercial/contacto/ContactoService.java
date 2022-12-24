package com.scing.erp.comercial.contacto;

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
public class ContactoService {

  private ContactoRepository contactoRepository;
  private ClienteRepository clienteRepository;
  private ModelMapper modelMapper;

  @Autowired
  public ContactoService(ContactoRepository contactoRepository, ClienteRepository clienteRepository,
      ModelMapper modelMapper) {
    this.contactoRepository = contactoRepository;
    this.clienteRepository = clienteRepository;
    this.modelMapper = modelMapper;
  }

  public List<ContactoDTO> listContacto() {

    List<Contacto> listContacto = this.contactoRepository.findAllByOrderByEliminadoAsc();

    List<ContactoDTO> listContactoDTO = listContacto.stream().map(contacto -> {

      ContactoDTO contactoDTO = new ContactoDTO();
      contactoDTO.setIdcontacto(contacto.getIdcontacto());
      contactoDTO.setNombre(contacto.getNombre());
      contactoDTO.setCorreo(contacto.getCorreo());
      contactoDTO.setTelefono(contacto.getTelefono());
      contactoDTO.setCelular(contacto.getCelular());
      contactoDTO.setCargo(contacto.getCargo());
      contactoDTO.setUnidad(contacto.getUnidad());
      contactoDTO.setEliminado(contacto.isEliminado());
      contactoDTO.setRazonsocial(contacto.getCliente().getRazonsocial());
      contactoDTO.setIdcliente(contacto.getCliente().getIdcliente());
      return contactoDTO;

    }).collect(Collectors.toList());

    return listContactoDTO;
  }

  public List<ContactoDTO> listContactoByIdcliente(Long idcliente) {

    if (clienteRepository.findById(idcliente).isPresent()) {

      Cliente cliente = clienteRepository.findById(idcliente).orElse(null);

      List<Contacto> listContacto = this.contactoRepository.findAllByClienteOrderByEliminadoAsc(cliente);
      List<ContactoDTO> listContactoDTO = listContacto.stream().map(contacto -> {

        ContactoDTO contactoDTO = new ContactoDTO();

        contactoDTO.setIdcontacto(contacto.getIdcontacto());
        contactoDTO.setNombre(contacto.getNombre());
        contactoDTO.setCorreo(contacto.getCorreo());
        contactoDTO.setTelefono(contacto.getTelefono());
        contactoDTO.setCelular(contacto.getCelular());
        contactoDTO.setCargo(contacto.getCargo());
        contactoDTO.setUnidad(contacto.getUnidad());
        contactoDTO.setEliminado(contacto.isEliminado());
        contactoDTO.setRazonsocial(contacto.getCliente().getRazonsocial());
        contactoDTO.setIdcliente(contacto.getCliente().getIdcliente());

        return contactoDTO;
      }).collect(Collectors.toList());

      return listContactoDTO;
    } else {
      return null;
    }
  }

  @Transactional
  public ResponseMensaje createContacto(ContactoDTO contactoDTO) {

    Cliente cliente = clienteRepository.findById(contactoDTO.getIdcliente()).orElse(null);

    if (!contactoRepository.findByCorreo(contactoDTO.getCorreo()).isPresent()) {

      Contacto contacto = new Contacto();

      contacto.setNombre(contactoDTO.getNombre());
      contacto.setCorreo(contactoDTO.getCorreo());
      contacto.setTelefono(contactoDTO.getTelefono());
      contacto.setCelular(contactoDTO.getCelular());
      contacto.setCargo(contactoDTO.getCargo());
      contacto.setUnidad(contactoDTO.getUnidad());
      contacto.setEliminado(false);
      contacto.setCliente(cliente);

      contacto.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      contactoRepository.save(contacto);

      return new ResponseMensaje(200, "Contacto registrado");
    } else {
      return new ResponseMensaje(409, "Contacto con correo, " + contactoDTO.getCorreo() + " ya existe");
    }
  }

  public ContactoDTO getContactoByIdcontacto(Long idcontacto) {

    Contacto contacto = contactoRepository.findById(idcontacto).orElse(null);

    return modelMapper.map(contacto, ContactoDTO.class);
  }

  @Transactional
  public ResponseMensaje updateContacto(ContactoDTO contactoDTO) {

    Cliente cliente = clienteRepository.findById(contactoDTO.getIdcliente()).orElse(null);

    if (contactoRepository.findById(contactoDTO.getIdcontacto()).isPresent()) {

      Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);

      contacto.setNombre(contactoDTO.getNombre());
      contacto.setCorreo(contactoDTO.getCorreo());
      contacto.setTelefono(contactoDTO.getTelefono());
      contacto.setCelular(contactoDTO.getCelular());
      contacto.setCargo(contactoDTO.getCargo());
      contacto.setUnidad(contactoDTO.getUnidad());
      contacto.setEliminado(false);
      contacto.setCliente(cliente);
      contacto.setUupdate("SuperAdmin");

      contacto.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      contactoRepository.save(contacto);

      return new ResponseMensaje(200, "Contacto actualizado");
    } else {
      return new ResponseMensaje(404, "Contacto , " + contactoDTO.getIdcontacto() + " no encontrado");
    }
  }

  @Transactional
  public ResponseMensaje deleteContacto(Long idcontacto) {

    if (contactoRepository.findById(idcontacto).isPresent()) {

      Contacto contacto = contactoRepository.findById(idcontacto).orElse(null);

      String msj = "Contacto" + contacto.getNombre() + ", ha sido habilitado";

      if (contacto.isEliminado()) {
        contacto.setEliminado(false);
      } else {
        contacto.setEliminado(true);
        msj = "Contacto " + contacto.getNombre() + ", ha sido inhabilitado";
      }

      contacto.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      contactoRepository.save(contacto);

      return new ResponseMensaje(200, msj);
    } else {

      return new ResponseMensaje(404, "Contacto no encontrado");
    }
  }

}
