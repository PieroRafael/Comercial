package com.scing.erp.comercial.ot;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.scing.erp.comercial.documentoot.DocumentootService;
import com.scing.erp.comercial.spc.Spc;
import com.scing.erp.comercial.spc.SpcRepository;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class OtService {

  private OtRepository otRepository;
  private ModelMapper modelMapper;
  private SpcRepository spcRepository;
  private DocumentootService documentootService;

  @Autowired
  public OtService(OtRepository otRepository, SpcRepository spcRepository, ModelMapper modelMapper,
      DocumentootService documentootService) {
    this.otRepository = otRepository;
    this.spcRepository = spcRepository;
    this.modelMapper = modelMapper;
    this.documentootService = documentootService;
  }

  public List<OtDTO> listOt() {

    DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    List<Ot> listOt = this.otRepository.findAllByOrderByEliminadoAsc();

    List<OtDTO> listOtDTO = listOt.stream().map(ot -> {

      OtDTO otDTO = new OtDTO();
      otDTO.setIdot(ot.getIdot());
      otDTO.setCodigo(ot.getCodigo());
      otDTO.setTipoproyecto(ot.isTipoproyecto());
      otDTO.setFcreate(fecha.format(ot.getFcreate()));
      otDTO.setIdspc(ot.getSpc().getIdspc());
      otDTO.setProyecto(ot.getSpc().getProyecto());
      otDTO.setOtprincipal(ot.getOtprincipal());
      otDTO.setEliminado(ot.isEliminado());
      return otDTO;

    }).collect(Collectors.toList());

    return listOtDTO;
  }

  public List<OtDTO> listOtByOtprincipal(Long otprincipal) {

    DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    List<Ot> listOt = this.otRepository.findAllByOtprincipal(otprincipal);

    List<OtDTO> listOtDTO = listOt.stream().map(ot -> {

      OtDTO otDTO = new OtDTO();
      otDTO.setIdot(ot.getIdot());
      otDTO.setCodigo(ot.getCodigo());
      otDTO.setTipoproyecto(ot.isTipoproyecto());
      otDTO.setFcreate(fecha.format(ot.getFcreate()));
      otDTO.setOtprincipal(ot.getOtprincipal());
      otDTO.setProyecto(ot.getSpc().getProyecto());
      otDTO.setEliminado(ot.isEliminado());
      return otDTO;

    }).collect(Collectors.toList());

    return listOtDTO;
  }

  public List<OtSelectDTO> listSelectOt() {

    List<Ot> listOt = this.otRepository.findAllByEliminadoAndTipoproyecto(false, false);

    List<OtSelectDTO> listOtSelectDTO = listOt.stream().map(ot -> {

      OtSelectDTO otSelectDTO = new OtSelectDTO();
      otSelectDTO.setIdot(ot.getIdot());
      otSelectDTO.setCodigo(ot.getCodigo());
      return otSelectDTO;

    }).collect(Collectors.toList());

    return listOtSelectDTO;
  }

  @Transactional
  public ResponseMensaje createOt(Long idspc, String codigo, Boolean tipoproyecto, Long otprincipal,
      MultipartFile[] listFile) {

    Spc spc = spcRepository.findById(idspc).orElse(null);

    if (!otRepository.findByCodigo(codigo).isPresent()) {

      Ot ot = new Ot();

      ot.setCodigo(codigo);
      ot.setSpc(spc);
      if (tipoproyecto) {
        ot.setTipoproyecto(tipoproyecto);
        ot.setOtprincipal(otprincipal);
      }
      ot.setEliminado(false);

      ot.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      Ot savedOt = otRepository.save(ot);

      Arrays.asList(listFile).stream().forEach(file -> {

        List<String> listArea = new ArrayList<String>();
        try {
          documentootService.createDocumentoot(listArea, file, savedOt.getIdot(), "Archivo requerido");
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

      return new ResponseMensaje(200, "OT registrada");
    } else {
      return new ResponseMensaje(409, "Ot con código, " + codigo + " ya existe");
    }
  }

  public OtDTO getOtByIdot(Long idot) {

    DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Ot ot = otRepository.findById(idot).orElse(null);

    OtDTO otDTO = modelMapper.map(ot, OtDTO.class);

    otDTO.setFcreate(fecha.format(ot.getFcreate()));
    otDTO.setCodigospc(ot.getSpc().getCodigo());

    return otDTO;
  }

  @Transactional
  public ResponseMensaje updateOt(OtDTO otDTO) {

    Spc spc = spcRepository.findById(otDTO.getIdspc()).orElse(null);

    if (otRepository.findById(otDTO.getIdot()).isPresent()) {

      Ot ot = otRepository.findById(otDTO.getIdot()).orElse(null);

      ot.setCodigo(otDTO.getCodigo());
      ot.setTipoproyecto(otDTO.isTipoproyecto());
      ot.setOtprincipal(otDTO.getOtprincipal());
      ot.setSpc(spc);
      ot.setUupdate("SuperAdmin");
      /*
       * ot.setUupdate(UserContextHolder.getUser().getNombres() +
       * UserContextHolder.getUser().getApellidos());
       */
      otRepository.save(ot);
      return new ResponseMensaje(200, "Ot actualizado");

    } else {

      return new ResponseMensaje(404, "Ot , " + otDTO.getIdot() + " no encontrado");
    }
  }

  @Transactional
  public ResponseMensaje deleteOt(Long idot) {

    if (otRepository.findById(idot).isPresent()) {

      Ot ot = otRepository.findById(idot).orElse(null);

      String msj = "Ot" + ot.getCodigo() + ", ha sido habilitado";

      if (ot.isEliminado()) {
        ot.setEliminado(false);
      } else {
        ot.setEliminado(true);
        msj = "Ot " + ot.getCodigo() + ", ha sido inhabilitado";
      }
      ot.setUupdate("SuperAdmin");
      /*
       * ot.setUupdate(UserContextHolder.getUser().getNombres() +
       * UserContextHolder.getUser().getApellidos());
       */
      otRepository.save(ot);
      return new ResponseMensaje(200, msj);

    } else {

      return new ResponseMensaje(404, "Ot no encontrado");
    }
  }
}
