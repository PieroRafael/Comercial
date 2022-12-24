package com.scing.erp.comercial.spc;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.comercial.cliente.Cliente;
import com.scing.erp.comercial.cliente.ClienteRepository;
import com.scing.erp.comercial.contacto.Contacto;
import com.scing.erp.comercial.contacto.ContactoDTO;
import com.scing.erp.comercial.contacto.ContactoRepository;
import com.scing.erp.comercial.detallecontacto.Spccontacto;
import com.scing.erp.comercial.detallecontacto.SpccontactoRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class SpcService {

  private SpcRepository spcRepository;
  private ClienteRepository clienteRepository;
  private ModelMapper modelMapper;
  private ContactoRepository contactoRepository;
  private SpccontactoRepository spccontactoRepository;

  @Autowired
  public SpcService(SpcRepository spcRepository, ClienteRepository clienteRepository, ModelMapper modelMapper,
      ContactoRepository contactoRepository, SpccontactoRepository spccontactoRepository) {
    this.spcRepository = spcRepository;
    this.clienteRepository = clienteRepository;
    this.modelMapper = modelMapper;
    this.contactoRepository = contactoRepository;
    this.spccontactoRepository = spccontactoRepository;
  }

  public List<SpcDTO> listSpc() {

    DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    List<Spc> listSpc = this.spcRepository.findAllByOrderByEliminadoAsc();

    List<SpcDTO> listSpcDTO = listSpc.stream().map(spc -> {

      SpcDTO spcDTO = new SpcDTO();
      spcDTO.setIdspc(spc.getIdspc());
      spcDTO.setCodigo(spc.getCodigo());
      spcDTO.setFcreate(fecha.format(spc.getFcreate()));
      spcDTO.setProyecto(spc.getProyecto());
      spcDTO.setVendedor(spc.getVendedor());
      spcDTO.setTipo(spc.getTipo());
      spcDTO.setEliminado(spc.isEliminado());
      spcDTO.setRazonsocial(spc.getCliente().getRazonsocial());
      spcDTO.setIdcliente(spc.getCliente().getIdcliente());
      spcDTO.setFechaabsolucion(spc.getFechaabsolucion());
      spcDTO.setFechaentrega(spc.getFechaentrega());
      spcDTO.setFechaenviodeconsulta(spc.getFechaenviodeconsulta());
      spcDTO.setFechareunion(spc.getFechareunion());
      spcDTO.setFechavisitatecnica(spc.getFechavisitatecnica());
      spcDTO.setUbicacion(spc.getUbicacion());

      return spcDTO;

    }).collect(Collectors.toList());

    return listSpcDTO;
  }

  public List<SpcSelectDTO> listSelectSpc() {

    List<Spc> listSpc = this.spcRepository.findAllByEliminado();

    List<SpcSelectDTO> listSpcSelectDTO = listSpc.stream().map(spc -> {

      SpcSelectDTO spcSelectDTO = new SpcSelectDTO();
      spcSelectDTO.setIdspc(spc.getIdspc());
      spcSelectDTO.setCodigo(spc.getCodigo());

      return spcSelectDTO;

    }).collect(Collectors.toList());

    return listSpcSelectDTO;
  }

  @Transactional
  public ResponseMensaje createSpc(SpcDTO spcDTO) {

    Cliente cliente = clienteRepository.findById(spcDTO.getIdcliente()).orElse(null);

    if (!spcRepository.findByCodigo(spcDTO.getCodigo()).isPresent()) {

      Spc spc = new Spc();
      spc.setCodigo(spcDTO.getCodigo());
      spc.setProyecto(spcDTO.getProyecto());
      spc.setVendedor(spcDTO.getVendedor());
      spc.setTipo(spcDTO.getTipo());
      spc.setFechaabsolucion(spcDTO.getFechaabsolucion());
      spc.setFechaentrega(spcDTO.getFechaentrega());
      spc.setFechaenviodeconsulta(spcDTO.getFechaenviodeconsulta());
      spc.setFechareunion(spcDTO.getFechareunion());
      spc.setFechavisitatecnica(spcDTO.getFechavisitatecnica());
      spc.setUbicacion(spcDTO.getUbicacion());
      spc.setEliminado(false);
      spc.setCliente(cliente);
      spc.setUcreate("SuperAdmin");

      spc.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      Spc saveSpc = spcRepository.save(spc);

      spcDTO.getConsulta().forEach(contactoDTO -> {
        Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);
        Spccontacto spccontacto = new Spccontacto();
        spccontacto.setSpc(saveSpc);
        spccontacto.setContacto(contacto);
        spccontacto.setOcupacion("consulta");
        spccontactoRepository.save(spccontacto);
      });

      spcDTO.getTecnica().forEach(contactoDTO -> {
        Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);
        Spccontacto spccontacto = new Spccontacto();
        spccontacto.setSpc(saveSpc);
        spccontacto.setContacto(contacto);
        spccontacto.setOcupacion("tecnica");
        spccontactoRepository.save(spccontacto);
      });

      spcDTO.getVisita().forEach(contactoDTO -> {
        Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);
        Spccontacto spccontacto = new Spccontacto();
        spccontacto.setSpc(saveSpc);
        spccontacto.setContacto(contacto);
        spccontacto.setOcupacion("visita");
        spccontactoRepository.save(spccontacto);
      });

      createFolder(spcDTO.getCodigo());

      return new ResponseMensaje(200, "SPC registrada");

    } else {

      return new ResponseMensaje(409, "SPC con código, " + spcDTO.getCodigo() + " ya existe");
    }
  }

  public SpcDTO getSpcByIdspc(Long idspc) {

    DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Spc spc = spcRepository.findById(idspc).orElse(null);

    SpcDTO spcDTO = modelMapper.map(spc, SpcDTO.class);

    List<ContactoDTO> consulta = new ArrayList<>();
    List<ContactoDTO> tecnica = new ArrayList<>();
    List<ContactoDTO> visita = new ArrayList<>();

    spccontactoRepository.findBySpc(spc).forEach(spccontacto -> {

      ContactoDTO contactoDTO = new ContactoDTO();

      if (spccontacto.getOcupacion().equals("consulta")) {
        contactoDTO.setIdcontacto(spccontacto.getContacto().getIdcontacto());
        contactoDTO.setCorreo(spccontacto.getContacto().getCorreo());
        contactoDTO.setTelefono(spccontacto.getContacto().getTelefono());
        contactoDTO.setNombre(spccontacto.getContacto().getNombre());
        consulta.add(contactoDTO);
      }
      if (spccontacto.getOcupacion().equals("tecnica")) {
        contactoDTO.setIdcontacto(spccontacto.getContacto().getIdcontacto());
        contactoDTO.setCorreo(spccontacto.getContacto().getCorreo());
        contactoDTO.setTelefono(spccontacto.getContacto().getTelefono());
        contactoDTO.setNombre(spccontacto.getContacto().getNombre());
        tecnica.add(contactoDTO);
      }
      if (spccontacto.getOcupacion().equals("visita")) {
        contactoDTO.setIdcontacto(spccontacto.getContacto().getIdcontacto());
        contactoDTO.setCorreo(spccontacto.getContacto().getCorreo());
        contactoDTO.setTelefono(spccontacto.getContacto().getTelefono());
        contactoDTO.setNombre(spccontacto.getContacto().getNombre());
        visita.add(contactoDTO);
      }
    });
    spcDTO.setFcreate(fecha.format(spc.getFcreate()));
    spcDTO.setConsulta(consulta);
    spcDTO.setTecnica(tecnica);
    spcDTO.setVisita(visita);
    return spcDTO;
  }

  @Transactional
  public ResponseMensaje updateSpc(SpcDTO spcDTO) {

    Cliente cliente = clienteRepository.findById(spcDTO.getIdcliente()).orElse(null);

    if (spcRepository.findById(spcDTO.getIdspc()).isPresent()) {

      Spc spc = spcRepository.findById(spcDTO.getIdspc()).orElse(null);

      spc.setVendedor(spcDTO.getVendedor());
      spc.setTipo(spcDTO.getTipo());
      spc.setCliente(cliente);
      spc.setFechaenviodeconsulta(spcDTO.getFechaenviodeconsulta());
      spc.setFechaabsolucion(spcDTO.getFechaabsolucion());
      spc.setFechaentrega(spcDTO.getFechaentrega());
      spc.setFechareunion(spcDTO.getFechareunion());
      spc.setFechavisitatecnica(spcDTO.getFechavisitatecnica());
      spc.setUbicacion(spcDTO.getUbicacion());

      spc.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      Spc updateSpc = spcRepository.save(spc);

      spccontactoRepository.deleteBySpc(updateSpc);

      spcDTO.getConsulta().forEach(contactoDTO -> {
        Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);
        Spccontacto spccontacto = new Spccontacto();
        spccontacto.setSpc(updateSpc);
        spccontacto.setContacto(contacto);
        spccontacto.setOcupacion("consulta");
        spccontactoRepository.save(spccontacto);
      });

      spcDTO.getTecnica().forEach(contactoDTO -> {
        Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);
        Spccontacto spccontacto = new Spccontacto();
        spccontacto.setSpc(updateSpc);
        spccontacto.setContacto(contacto);
        spccontacto.setOcupacion("tecnica");
        spccontactoRepository.save(spccontacto);
      });

      spcDTO.getVisita().forEach(contactoDTO -> {
        Contacto contacto = contactoRepository.findById(contactoDTO.getIdcontacto()).orElse(null);
        Spccontacto spccontacto = new Spccontacto();
        spccontacto.setSpc(updateSpc);
        spccontacto.setContacto(contacto);
        spccontacto.setOcupacion("visita");
        spccontactoRepository.save(spccontacto);
      });

      return new ResponseMensaje(200, "Spc actualizado");

    } else {

      return new ResponseMensaje(404, "Spc , " + spcDTO.getIdspc() + " no encontrado");
    }
  }

  @Transactional
  public ResponseMensaje deleteSpc(Long idspc) {

    if (spcRepository.findById(idspc).isPresent()) {

      Spc spc = spcRepository.findById(idspc).orElse(null);

      String msj = "Spc" + spc.getCodigo() + ", ha sido habilitado";

      if (spc.isEliminado()) {
        spc.setEliminado(false);
      } else {
        spc.setEliminado(true);
        msj = "Spc " + spc.getCodigo() + ", ha sido inhabilitado";
      }
      spc.setUupdate("SuperAdmin");
      /*
       * spc.setUupdate(UserContextHolder.getUser().getNombres() +
       * UserContextHolder.getUser().getApellidos());
       */
      spcRepository.save(spc);
      return new ResponseMensaje(200, msj);

    } else {

      return new ResponseMensaje(404, "Spc no encontrado");
    }
  }

  private void createFolder(String codigo) {

    String[] rutas = { "01. FORMATOS", "02. DOCUMENTOS", "03. PLANOS", "04. PLANTILLA", "05. CONSULTAS Y RESPUESTAS",
        "06. IMAGENES" };

    for (String var : rutas) {

      StringBuilder builder = new StringBuilder();
      builder.append(System.getProperty("user.home"));
      builder.append(File.separator);
      builder.append("documentospc");
      builder.append(File.separator);
      builder.append(codigo);
      builder.append(File.separator);
      builder.append(var);

      File directorio = new File(builder.toString());
      if (!directorio.exists()) {
        if (directorio.mkdirs()) {
          System.out.println("Ruta: " + var + ", creada");
        } else {
          System.out.println("Error al crear directorio");
        }
      }
    }
  }
}
