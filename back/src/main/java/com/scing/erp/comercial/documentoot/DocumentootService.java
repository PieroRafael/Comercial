package com.scing.erp.comercial.documentoot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.comercial.ot.Ot;
import com.scing.erp.comercial.ot.OtRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.miscelaneos.area.Area;
import com.scing.erp.sistema.miscelaneos.area.AreaRepository;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class DocumentootService {

  private DocumentootRepository documentootRepository;
  private AreaRepository areaRepository;
  private OtRepository otRepository;

  public DocumentootService(DocumentootRepository documentootRepository, AreaRepository areaRepository,
      OtRepository otRepository) {
    this.documentootRepository = documentootRepository;
    this.areaRepository = areaRepository;
    this.otRepository = otRepository;
  }

  public List<DocumentootDTO> listDocumentoot(Long idot) {

    if (otRepository.findById(idot).isPresent()) {

      Ot ot = otRepository.findById(idot).orElse(null);

      List<Documentoot> listDocumentoot = this.documentootRepository.findAllByOtOrderByEliminadoAsc(ot);

      List<DocumentootDTO> listDocumentootDTO = listDocumentoot.stream().map(documentoot -> {

        DocumentootDTO documentootDTO = new DocumentootDTO();
        documentootDTO.setIddocumentoot(documentoot.getIddocumentoot());
        documentootDTO.setDescripcion(documentoot.getDescripcion());
        documentootDTO.setNombre(documentoot.getNombre());
        documentootDTO.setUrl(documentoot.getUrl());
        documentootDTO.setFcreate(documentoot.getFcreate());
        documentootDTO.setUcreate(documentoot.getUcreate());
        documentootDTO.setEliminado(documentoot.isEliminado());

        List<String> listarea = documentoot.getArea().stream().map(area -> {
          return area.getNombre();
        }).collect(Collectors.toList());
        documentootDTO.setListArea(listarea);
        return documentootDTO;

      }).collect(Collectors.toList());

      return listDocumentootDTO;
    } else {

      return null;
    }
  }

  @Transactional
  public ResponseMensaje createDocumentoot(List<String> listArea, MultipartFile file, Long idot, String descripcion)
      throws IOException {

    ResponseMensaje doc = this.storeFile(file, idot);
    String nombre = file.getOriginalFilename();

    if (doc == null) {

      if (otRepository.findById(idot).isPresent()) {

        Ot ot = otRepository.findById(idot).orElse(null);

        if (!documentootRepository.findByNombreAndOt(nombre, ot).isPresent()) {

          Documentoot documentoot = new Documentoot();
          documentoot.setDescripcion(descripcion);
          documentoot.setNombre(nombre);
          documentoot.setUrl(idot + "-" + file.getOriginalFilename());
          documentoot.setOt(ot);
          documentoot.setEliminado(false);

          documentoot.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

          Set<Area> hash_Set = new HashSet<Area>();
          if (!listArea.isEmpty()) {
            listArea.forEach(area -> {
              hash_Set.add(areaRepository.findByNombre(area));
            });
            documentoot.setArea(hash_Set);
          }

          documentootRepository.save(documentoot);

          return new ResponseMensaje(200, "Documento registrado");
        } else {
          return new ResponseMensaje(409, "Documento con nombre, " + nombre + " ya existe");
        }
      } else {
        return new ResponseMensaje(404, "Ot con id, " + idot + " no encontrado");
      }
    } else {
      return doc;
    }
  }

  public DocumentootDTO getDocumentootByIddocumentoot(Long iddocumentoot) {

    if (documentootRepository.findById(iddocumentoot).isPresent()) {

      Documentoot documentoot = documentootRepository.findById(iddocumentoot).orElse(null);

      DocumentootDTO documentootDTO = new DocumentootDTO();

      List<String> listarea = documentoot.getArea().stream().map(area -> {
        return area.getNombre();

      }).collect(Collectors.toList());

      documentootDTO.setListArea(listarea);
      documentootDTO.setDescripcion(documentoot.getDescripcion());
      documentootDTO.setIddocumentoot(documentoot.getIddocumentoot());
      documentootDTO.setIdot(documentoot.getOt().getIdot());
      return documentootDTO;

    } else {

      return null;
    }
  }

  @Transactional
  public ResponseMensaje updateDocumentoot(DocumentootDTO documentootDTO) {

    if (documentootRepository.findById(documentootDTO.getIddocumentoot()).isPresent()) {

      Documentoot documentoot = documentootRepository.findById(documentootDTO.getIddocumentoot()).orElse(null);

      documentoot.setDescripcion(documentootDTO.getDescripcion());

      Set<Area> hash_Set = new HashSet<Area>();
      if (!documentootDTO.getListArea().isEmpty()) {
        documentootDTO.getListArea().forEach(area -> {
          hash_Set.add(areaRepository.findByNombre(area));
        });
        documentoot.setArea(hash_Set);
      }

      documentoot.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      documentootRepository.save(documentoot);

      return new ResponseMensaje(200, "Documento actualizado");
    } else {
      return new ResponseMensaje(404, "Documento no encontrado");
    }
  }

  @Transactional
  public ResponseMensaje deleteDocumentoot(Long iddocumentoot) {

    if (documentootRepository.findById(iddocumentoot).isPresent()) {

      Documentoot documentoot = documentootRepository.findById(iddocumentoot).orElse(null);

      String msj = "Documento" + documentoot.getNombre() + ", ha sido habilitado";

      if (documentoot.isEliminado()) {
        documentoot.setEliminado(false);
      } else {
        documentoot.setEliminado(true);
        msj = "Documento " + documentoot.getNombre() + ", ha sido inhabilitado";
      }

      documentoot.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      documentootRepository.save(documentoot);

      return new ResponseMensaje(200, msj);
    } else {
      return new ResponseMensaje(404, "Documento no encontrado");
    }
  }

  public ResponseMensaje storeFile(MultipartFile file, Long idot) {

    try {
      String fileName = StringUtils.cleanPath(idot + "-" + file.getOriginalFilename());

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
