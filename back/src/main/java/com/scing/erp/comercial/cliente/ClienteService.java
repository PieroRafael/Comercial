package com.scing.erp.comercial.cliente;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class ClienteService {

  private ClienteRepository clienteRepository;
  private ModelMapper modelMapper;

  @Autowired
  public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
    this.clienteRepository = clienteRepository;
    this.modelMapper = modelMapper;
  }

  public List<ClienteDTO> listCliente() {

    List<Cliente> listCliente = this.clienteRepository.findAllByOrderByEliminadoAsc();

    List<ClienteDTO> listClienteDTO = listCliente.stream().map(cliente -> {

      ClienteDTO clienteDTO = new ClienteDTO();
      clienteDTO.setIdcliente(cliente.getIdcliente());
      clienteDTO.setRuc(cliente.getRuc());
      clienteDTO.setRazonsocial(cliente.getRazonsocial());
      clienteDTO.setTelefono(cliente.getTelefono());
      clienteDTO.setDireccion(cliente.getDireccion());
      clienteDTO.setPaginaweb(cliente.getPaginaweb());
      clienteDTO.setEliminado(cliente.isEliminado());
      return clienteDTO;

    }).collect(Collectors.toList());

    return listClienteDTO;
  }

  public List<ClienteSelectDTO> listSelectCliente() {

    List<Cliente> listCliente = this.clienteRepository.findAllByEliminado(false);

    List<ClienteSelectDTO> listClienteSelectDTO = listCliente.stream().map(cliente -> {

      ClienteSelectDTO clienteSelectDTO = new ClienteSelectDTO();
      clienteSelectDTO.setIdcliente(cliente.getIdcliente());
      clienteSelectDTO.setRazonsocial(cliente.getRazonsocial());
      return clienteSelectDTO;

    }).collect(Collectors.toList());

    return listClienteSelectDTO;
  }

  @Transactional
  public ResponseMensaje createCliente(ClienteDTO clienteDTO) {

    if (!clienteRepository.findByRuc(clienteDTO.getRuc()).isPresent()) {

      Cliente cliente = new Cliente();
      cliente.setRuc(clienteDTO.getRuc());
      cliente.setRazonsocial(clienteDTO.getRazonsocial());
      cliente.setTelefono(clienteDTO.getTelefono());
      cliente.setDireccion(clienteDTO.getDireccion());
      cliente.setPaginaweb(clienteDTO.getPaginaweb());
      cliente.setEliminado(false);

      cliente.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      clienteRepository.save(cliente);

      return new ResponseMensaje(200, "Cliente registrado");
    } else {
      return new ResponseMensaje(409, "Cliente con RUC, " + clienteDTO.getRuc() + " ya existe");
    }
  }

  public ClienteDTO getClienteByIdcliente(Long idcliente) {
    Cliente cliente = clienteRepository.findById(idcliente).orElse(null);
    return modelMapper.map(cliente, ClienteDTO.class);
  }

  @Transactional
  public ResponseMensaje updateCliente(ClienteDTO clienteDTO) {

    if (clienteRepository.findById(clienteDTO.getIdcliente()).isPresent()) {

      Cliente cliente = clienteRepository.findById(clienteDTO.getIdcliente()).orElse(null);

      if (cliente.getRuc().equals(clienteDTO.getRuc())
          || !clienteRepository.findByRuc(clienteDTO.getRuc()).isPresent()) {

        cliente.setRuc(clienteDTO.getRuc());
        cliente.setRazonsocial(clienteDTO.getRazonsocial());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setPaginaweb(clienteDTO.getPaginaweb());

        cliente.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

        clienteRepository.save(cliente);

        return new ResponseMensaje(200, "Cliente actualizado");
      } else {
        return new ResponseMensaje(409, "Cliente con RUC, " + clienteDTO.getRuc() + " ya existe");
      }
    } else {
      return new ResponseMensaje(404, "Cliente con id, " + clienteDTO.getIdcliente() + " no encontrado");
    }
  }

  @Transactional
  public ResponseMensaje deleteCliente(Long idcliente) {

    if (clienteRepository.findById(idcliente).isPresent()) {

      Cliente cliente = clienteRepository.findById(idcliente).orElse(null);

      String msj = "Cliente" + cliente.getRazonsocial() + ", ha sido habilitado";

      if (cliente.isEliminado()) {
        cliente.setEliminado(false);
      } else {
        cliente.setEliminado(true);
        msj = "Cliente " + cliente.getRazonsocial() + ", ha sido inhabilitado";
      }

      cliente.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      clienteRepository.save(cliente);

      return new ResponseMensaje(200, msj);
    } else {
      return new ResponseMensaje(404, "Cliente no encontrado");
    }
  }
}
