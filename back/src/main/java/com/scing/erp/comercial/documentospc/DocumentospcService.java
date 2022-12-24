package com.scing.erp.comercial.documentospc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import com.scing.erp.comercial.seguimientospc.Seguimientospc;
import com.scing.erp.comercial.seguimientospc.SeguimientospcRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class DocumentospcService {

  private DocumentospcRepository documentospcRepository;
  private SeguimientospcRepository seguimientospcRepository;

  @Autowired
  public DocumentospcService(DocumentospcRepository documentospcRepository,
      SeguimientospcRepository seguimientospcRepository) {
    this.documentospcRepository = documentospcRepository;
    this.seguimientospcRepository = seguimientospcRepository;
  }

  public List<DocumentospcDTO> listDocumentospc(Long idseguimientospc, String rutabase) {

    if (seguimientospcRepository.findById(idseguimientospc).isPresent()) {

      Seguimientospc seguimientospc = seguimientospcRepository.findById(idseguimientospc).orElse(null);

      List<Documentospc> listDocumentospc = this.documentospcRepository.findAllBySeguimientospc(seguimientospc);

      List<DocumentospcDTO> listDocumentospcDTO = new ArrayList<DocumentospcDTO>();

      listDocumentospc.forEach(documentospc -> {

        DocumentospcDTO documentospcDTO = new DocumentospcDTO();

        if (rutabase.equals("null")) {
          if (documentospc.getUrl().split("/").length == 1) {
            documentospcDTO.setTipo(true);
            documentospcDTO.setNombre(documentospc.getNombre());
            documentospcDTO.setUrl(documentospc.getUrl());
            listDocumentospcDTO.add(documentospcDTO);
          } else {
            if (documentospc.getUrl().split("/").length == 2) {

              String nombreFolder = documentospc.getUrl().split("/")[1];
              boolean eval = false;

              for (DocumentospcDTO temp : listDocumentospcDTO) {
                if (temp.getNombre().equals(nombreFolder)) {
                  eval = true;
                }
              }

              if (!eval) {

                documentospcDTO.setTipo(false);
                documentospcDTO.setNombre(nombreFolder);
                documentospcDTO.setUrl(documentospc.getUrl());
                listDocumentospcDTO.add(documentospcDTO);
              }
            }
          }
        } else {
          if (rutabase.equals(documentospc.getUrl())) {

            documentospcDTO.setTipo(true);
            documentospcDTO.setNombre(documentospc.getNombre());
            documentospcDTO.setUrl(documentospc.getUrl());
            listDocumentospcDTO.add(documentospcDTO);
          } else {

            Integer lRutabase = rutabase.split("/").length;
            Integer lUrl = documentospc.getUrl().split("/").length;

            if (lUrl == lRutabase + 1) {

              System.out.println("URL: " + documentospc.getUrl());

              String nombreFolder = documentospc.getUrl().split("/")[lUrl-1];
              System.out.println("nombreFolder: " + nombreFolder);
              boolean eval = false;

              for (DocumentospcDTO temp : listDocumentospcDTO) {
                if (temp.getNombre().equals(nombreFolder)) {
                  eval = true;
                }
              }

              if (!eval) {

                documentospcDTO.setTipo(false);
                documentospcDTO.setNombre(nombreFolder);
                documentospcDTO.setUrl(documentospc.getUrl());
                listDocumentospcDTO.add(documentospcDTO);
              }
            }
          }
        }
      });

      return listDocumentospcDTO;
    } else {
      return null;
    }
  }

  @Transactional
  public ResponseMensaje createDocumentospc(MultipartFile file, Long idseguimientospc, String url) throws IOException {

    String arrayNombreFile[] = file.getOriginalFilename().split("/");
    String ruta = "";
    for (int i = 0; i < arrayNombreFile.length - 1; i++) {
      ruta += arrayNombreFile[i] + "/";
    }
    String nombreFile = arrayNombreFile[arrayNombreFile.length - 1];

    ResponseMensaje doc = this.storeFile(file, url, nombreFile);

    if (doc == null) {

      if (seguimientospcRepository.findById(idseguimientospc).isPresent()) {

        Seguimientospc seguimientospc = seguimientospcRepository.findById(idseguimientospc).orElse(null);

        if (!documentospcRepository.findByNombreAndSeguimientospc(nombreFile, seguimientospc).isPresent()) {

          Documentospc documentospc = new Documentospc();

          documentospc.setNombre(nombreFile);
          documentospc.setUrl(ruta);
          documentospc.setSeguimientospc(seguimientospc);

          documentospc
              .setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

          documentospcRepository.save(documentospc);

          return new ResponseMensaje(200, "Documento registrado");
        } else {
          return new ResponseMensaje(409, "Documento con nombre, " + nombreFile + " ya existe");
        }
      } else {
        return new ResponseMensaje(404, "Seguimientospc con id, " + idseguimientospc + " no encontrado");
      }
    } else {
      return doc;
    }
  }

  private ResponseMensaje storeFile(MultipartFile file, String url, String nombreFile) throws IOException {

    try {
      String fileName = StringUtils.cleanPath(nombreFile);

      StringBuilder builder = new StringBuilder();

      builder.append(url);
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
