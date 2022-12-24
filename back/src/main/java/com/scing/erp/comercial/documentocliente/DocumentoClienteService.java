package com.scing.erp.comercial.documentocliente;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.comercial.cliente.Cliente;
import com.scing.erp.comercial.cliente.ClienteRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class DocumentoclienteService {

  private DocumentoclienteRepository documentoClienteRepository;
  private ClienteRepository clienteRepository;

  @Autowired
  public DocumentoclienteService(DocumentoclienteRepository documentoClienteRepository,
      ClienteRepository clienteRepository) {
    this.documentoClienteRepository = documentoClienteRepository;
    this.clienteRepository = clienteRepository;
  }

  public List<DocumentoclienteDTO> listDocumentocliente(Long idcliente) {

    if (clienteRepository.findById(idcliente).isPresent()) {

      Cliente cliente = clienteRepository.findById(idcliente).orElse(null);

      List<Documentocliente> listDocumentocliente = this.documentoClienteRepository
          .findAllByClienteOrderByEliminadoAsc(cliente);

      List<DocumentoclienteDTO> listDocumentoclienteDTO = listDocumentocliente.stream().map(documentocliente -> {

        DocumentoclienteDTO documentoclienteDTO = new DocumentoclienteDTO();
        documentoclienteDTO.setIddocumentocliente(documentocliente.getIddocumentocliente());
        documentoclienteDTO.setNombre(documentocliente.getNombre());
        documentoclienteDTO.setDescripcion(documentocliente.getDescripcion());
        documentoclienteDTO.setUrl(documentocliente.getUrl());
        documentoclienteDTO.setEliminado(documentocliente.isEliminado());
        documentoclienteDTO.setFcreate(documentocliente.getFcreate());
        documentoclienteDTO.setUcreate(documentocliente.getUcreate());
        return documentoclienteDTO;

      }).collect(Collectors.toList());

      return listDocumentoclienteDTO;
    } else {

      return null;
    }
  }

  @Transactional
  public ResponseMensaje createDocumentocliente(MultipartFile file, Long idcliente, String descripcion)
      throws IOException {

    ResponseMensaje doc = this.storeFile(file, idcliente);
    String nombre = file.getOriginalFilename();

    if (doc == null) {

      if (clienteRepository.findById(idcliente).isPresent()) {

        Cliente cliente = clienteRepository.findById(idcliente).orElse(null);

        if (!documentoClienteRepository.findByNombreAndCliente(nombre, cliente).isPresent()) {

          Documentocliente documentocliente = new Documentocliente();
          documentocliente.setNombre(nombre);
          documentocliente.setDescripcion(descripcion);
          documentocliente.setUrl(idcliente + "-" + file.getOriginalFilename());
          documentocliente.setEliminado(false);
          documentocliente.setCliente(cliente);

          documentocliente
              .setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());
          documentoClienteRepository.save(documentocliente);

          return new ResponseMensaje(200, "Documento registrado");
        } else {
          return new ResponseMensaje(409, "Documento con nombre, " + nombre + " ya existe");
        }
      } else {
        return new ResponseMensaje(404, "Cliente con id, " + idcliente + " no encontrado");
      }
    } else {
      return doc;
    }
  }

  @Transactional
  public ResponseMensaje deleteDocumentocliente(Long iddocumentocliente) {

    if (documentoClienteRepository.findById(iddocumentocliente).isPresent()) {

      Documentocliente documentocliente = documentoClienteRepository.findById(iddocumentocliente).orElse(null);

      String msj = "Documento" + documentocliente.getNombre() + ", ha sido habilitado";

      if (documentocliente.isEliminado()) {
        documentocliente.setEliminado(false);
      } else {
        documentocliente.setEliminado(true);
        msj = "Documento " + documentocliente.getNombre() + ", ha sido inhabilitado";
      }

      documentocliente
          .setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());
      documentoClienteRepository.save(documentocliente);

      return new ResponseMensaje(200, msj);
    } else {
      return new ResponseMensaje(404, "Documento no encontrado");
    }
  }

  public ResponseMensaje storeFile(MultipartFile file, Long idcliente) throws IOException {

    try {
      String fileName = StringUtils.cleanPath(idcliente + "-" + file.getOriginalFilename());

      StringBuilder builder = new StringBuilder();
      builder.append(System.getProperty("user.home"));
      builder.append(File.separator);
      builder.append("docs");
      builder.append(File.separator);
      builder.append(fileName);
      byte[] fileBytes = file.getBytes();
      Path filePath = Paths.get(builder.toString());

      Files.write(filePath, fileBytes);
    } catch (IOException e) {
      return new ResponseMensaje(409, "El documento a guardar es inválido o excede el peso");
    }

    return null;
  }

}
