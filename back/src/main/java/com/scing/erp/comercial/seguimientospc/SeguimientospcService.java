package com.scing.erp.comercial.seguimientospc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.comercial.documentospc.DocumentospcService;
import com.scing.erp.comercial.spc.Spc;
import com.scing.erp.comercial.spc.SpcRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class SeguimientospcService {

  private SeguimientospcRepository seguimientospcRepository;
  private SpcRepository spcRepository;
  private DocumentospcService documentospcService;
  private ModelMapper modelMapper;

  public SeguimientospcService(SeguimientospcRepository seguimientospcRepository, SpcRepository spcRepository,
      DocumentospcService documentospcService, ModelMapper modelMapper) {
    this.seguimientospcRepository = seguimientospcRepository;
    this.spcRepository = spcRepository;
    this.documentospcService = documentospcService;
    this.modelMapper = modelMapper;
  }

  public List<SeguimientospcDTO> listSeguimientospc(Long idspc) {

    if (spcRepository.findById(idspc).isPresent()) {

      Spc spc = spcRepository.findById(idspc).orElse(null);

      List<Seguimientospc> listSeguimientospc = this.seguimientospcRepository.findAllBySpc(spc);

      List<SeguimientospcDTO> listSeguimientospcDTO = listSeguimientospc.stream().map(seguimientospc -> {

        SeguimientospcDTO seguimientospcDTO = new SeguimientospcDTO();
        seguimientospcDTO.setIdseguimientospc(seguimientospc.getIdseguimientospc());
        seguimientospcDTO.setDescripcion(seguimientospc.getDescripcion());
        seguimientospcDTO.setFormarecepcion(seguimientospc.getFormarecepcion());
        seguimientospcDTO.setObservacion(seguimientospc.getObservacion());
        seguimientospcDTO.setFcreate(seguimientospc.getFcreate());
        return seguimientospcDTO;

      }).collect(Collectors.toList());

      return listSeguimientospcDTO;
    } else {
      return null;
    }
  }

  @Transactional
  public ResponseMensaje createSeguimientospc(MultipartFile[] listFile, List<String> listFolder, Long idspc,
      String observacion, String descripcion, String formarecepcion) {

    Spc spc = spcRepository.findById(idspc).orElse(null);

    if (spc != null) {

      Seguimientospc seguimientospc = new Seguimientospc();
      seguimientospc.setObservacion(observacion);
      seguimientospc.setFormarecepcion(formarecepcion);
      seguimientospc.setDescripcion(descripcion);
      seguimientospc.setSpc(spc);

      seguimientospc.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      seguimientospc = seguimientospcRepository.save(seguimientospc);

      createFolder(listFile, listFolder, seguimientospc.getIdseguimientospc(), spc.getCodigo());

      return new ResponseMensaje(200, "Seguimiento de SPC registrado");
    } else {
      return new ResponseMensaje(404, "SPC no encontrada");
    }
  }

  public SeguimientospcDTO getSeguimientospcByIdseguimientospc(Long idseguimientospc) {

    Seguimientospc seguimientospc = seguimientospcRepository.findById(idseguimientospc).orElse(null);

    return modelMapper.map(seguimientospc, SeguimientospcDTO.class);
  }

  @Transactional
  public ResponseMensaje updateSeguimientospc(SeguimientospcDTO seguimientospcDTO) {

    if (seguimientospcRepository.findById(seguimientospcDTO.getIdseguimientospc()).isPresent()) {

      Seguimientospc seguimientospc = seguimientospcRepository.findById(seguimientospcDTO.getIdseguimientospc())
          .orElse(null);

      seguimientospc.setObservacion(seguimientospcDTO.getObservacion());
      seguimientospc.setFormarecepcion(seguimientospcDTO.getFormarecepcion());
      seguimientospc.setDescripcion(seguimientospcDTO.getDescripcion());

      seguimientospc.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      seguimientospcRepository.save(seguimientospc);

      return new ResponseMensaje(200, "Seguimientospc actualizado");
    } else {
      return new ResponseMensaje(404, "Seguimientospc , " + seguimientospcDTO.getIdseguimientospc() + " no encontrado");
    }
  }

  private void createFolder(MultipartFile[] listFile, List<String> listFolder, Long idseguimientospc, String codigo) {

    listFolder.forEach(folder -> {

      String ruta[] = folder.split("/");

      if (ruta.length > 1) {

        StringBuilder builder = new StringBuilder();
        builder.append(System.getProperty("user.home"));
        builder.append(File.separator);
        builder.append("documentospc");
        builder.append(File.separator);
        builder.append(codigo);

        for (int i = 0; i < ruta.length - 1; i++) {
          builder.append(File.separator);
          builder.append(ruta[i]);
        }

        File directorio = new File(builder.toString());
        if (!directorio.exists()) {
          if (!directorio.mkdirs()) {
            System.out.println("Error al crear directorio");
          }
        }

        Arrays.asList(listFile).stream().forEach(file -> {

          String arrayNombreFile[] = file.getOriginalFilename().split("/");
          String nombreFile = arrayNombreFile[arrayNombreFile.length - 1];

          if (nombreFile.equals(ruta[ruta.length - 1])) {
            try {
              documentospcService.createDocumentospc(file, idseguimientospc, builder.toString());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

        });
      }
    });
  }
}
