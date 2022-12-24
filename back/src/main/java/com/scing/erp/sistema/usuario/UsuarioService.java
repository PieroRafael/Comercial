package com.scing.erp.sistema.usuario;

import com.scing.erp.sistema.authentication.AuthenticationTokenService;
import com.scing.erp.sistema.entity.GridData;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.entity.builder.PageableBuilder;
import com.scing.erp.sistema.miscelaneos.area.Area;
import com.scing.erp.sistema.miscelaneos.area.AreaRepository;
import com.scing.erp.sistema.rol.Rol;
import com.scing.erp.sistema.rol.RolDTO;
import com.scing.erp.sistema.rol.RolRepository;
import com.scing.erp.sistema.usuario.specification.builder.UsuarioSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@SuppressWarnings({ "checkstyle:ParameterNumber" })
public class UsuarioService {

  private UsuarioRepository usuarioRepository;
  private RolRepository rolRepository;
  private AreaRepository areaRepository;
  private PasswordEncoder passwordEncoder;
  private ModelMapper modelMapper;
  private PageableBuilder pageableBuilder;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository, AreaRepository areaRepository,
      PasswordEncoder passwordEncoder, ModelMapper modelMapper, RolRepository rolRepository,
      AuthenticationTokenService authenticationTokenService, PageableBuilder pageableBuilder) {
    this.pageableBuilder = pageableBuilder;
    this.usuarioRepository = usuarioRepository;
    this.areaRepository = areaRepository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
    this.rolRepository = rolRepository;
  }

  public GridData<UsuarioDTO> listUsuario(UsuarioGridFilter filter) {
    UsuarioSpecificationBuilder specificationBuilder = new UsuarioSpecificationBuilder();

    Pageable paginationAndSort = pageableBuilder.build(filter, "usuario");
    Optional<Specification<Usuario>> optionalSpec = specificationBuilder.build(filter);
    Page<Usuario> pageUsuario = optionalSpec
        .map(usuarioSpecification -> usuarioRepository.findAll(usuarioSpecification, paginationAndSort))
        .orElseGet(() -> usuarioRepository.findAll(paginationAndSort));
    return parsePageToGridData(pageUsuario);
  }

  public List<UsuarioSelectDTO> listSelectVendedor() {

    List<Usuario> listUsuario = this.usuarioRepository.findAllByEliminadoAndVendedorOrderByApellidosAsc(false, true);

    List<UsuarioSelectDTO> listUsuarioSelectDTO = listUsuario.stream().map(usuario -> {

      UsuarioSelectDTO usuarioSelectDTO = new UsuarioSelectDTO();
      usuarioSelectDTO.setIdusuario(usuario.getIdusuario());
      usuarioSelectDTO.setNombre(usuario.getApellidos() + " " + usuario.getNombres());
      return usuarioSelectDTO;

    }).collect(Collectors.toList());

    return listUsuarioSelectDTO;
  }

  @Transactional
  public ResponseMensaje createUsuario(UsuarioDTO usuarioDTO) {

    Area area = areaRepository.findById(usuarioDTO.getArea().getIdarea()).orElse(null);

    if (area != null) {

      if (!usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {

        Usuario usuario = new Usuario();

        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setNick(usuarioDTO.getNick());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setCelular(usuarioDTO.getCelular());
        usuario.setPasswordHash(encodePassword(generarPassword()));
        usuario.setVendedor(usuarioDTO.getVendedor());
        usuario.setEliminado(false);

        usuario.setArea(area);

        Set<Rol> setRol = new HashSet<Rol>();
        if (!usuarioDTO.getRoles().isEmpty()) {
          usuarioDTO.getRoles().forEach(rol -> {
            setRol.add(rolRepository.findByEtiqueta(rol.getEtiqueta()));
          });
          usuario.setRoles(setRol);
        }

        usuario.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

        usuarioRepository.save(usuario);

        return new ResponseMensaje(200, "Usuario registrado");
      } else {
        return new ResponseMensaje(409, "Usuario con correo, " + usuarioDTO.getCorreo() + " ya existe");
      }
    } else {

      return new ResponseMensaje(404, "Area no encontrada");
    }
  }

  public UsuarioDTO getUsuarioByIdusuario(Long idusuario) {

    if (usuarioRepository.findById(idusuario).isPresent()) {

      Usuario usuario = usuarioRepository.findById(idusuario).orElse(null);

      UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

      List<RolDTO> listRolDTO = usuario.getRoles().stream().map(rol -> {

        RolDTO rolDTO = new RolDTO();
        rolDTO.setEtiqueta(rol.getEtiqueta());
        rolDTO.setSelected(true);

        return rolDTO;
      }).collect(Collectors.toList());

      usuarioDTO.setRoles(listRolDTO);

      return usuarioDTO;
    } else {
      return null;
    }
  }

  @Transactional
  public ResponseMensaje updateUsuario(UsuarioDTO usuarioDTO) {

    Area area = areaRepository.findById(usuarioDTO.getArea().getIdarea()).orElse(null);

    if (area != null) {
      if (usuarioRepository.findById(usuarioDTO.getIdusuario()).isPresent()) {

        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdusuario()).orElse(null);

        if (usuario.getCorreo().equals(usuarioDTO.getCorreo())
            || !usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {

          usuario.setNombres(usuarioDTO.getNombres());
          usuario.setApellidos(usuarioDTO.getApellidos());
          usuario.setNick(usuarioDTO.getNick());
          usuario.setCorreo(usuarioDTO.getCorreo());
          usuario.setCelular(usuarioDTO.getCelular());
          usuario.setVendedor(usuarioDTO.getVendedor());

          usuario.setArea(area);

          Set<Rol> setRol = new HashSet<Rol>();
          usuarioDTO.getRoles().forEach(rol -> {
            setRol.add(rolRepository.findByEtiqueta(rol.getEtiqueta()));
          });
          usuario.setRoles(setRol);

          usuario.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

          usuarioRepository.save(usuario);

          return new ResponseMensaje(200, "Usuario actualizado");
        } else {
          return new ResponseMensaje(409, "Usuario con correo, " + usuarioDTO.getCorreo() + " ya existe");
        }
      } else {
        return new ResponseMensaje(404, "Usuario no encontrado");
      }
    } else {

      return new ResponseMensaje(404, "Area no encontrada");
    }
  }

  @Transactional
  public ResponseMensaje deleteUsuario(Long idusuario) {

    if (usuarioRepository.findById(idusuario).isPresent()) {

      Usuario usuario = usuarioRepository.findById(idusuario).orElse(null);

      String msj = "Usuario " + usuario.getNick() + ", ha sido habilitado";

      if (usuario.isEliminado()) {

        usuario.setEliminado(false);
      } else {

        usuario.setEliminado(true);
        msj = "Usuario " + usuario.getNick() + ", ha sido inhabilitado";
      }

      usuario.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

      usuarioRepository.save(usuario);

      return new ResponseMensaje(200, msj);
    } else {
      return new ResponseMensaje(404, "Usuario no encontrado");
    }
  }

  private List<UsuarioDTO> mapUsuarioToUsuarioDTO(List<Usuario> listUsuario) {
    return listUsuario.stream().map(usuario -> {
      UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
      return usuarioDTO;
    }).collect(Collectors.toList());
  }

  private GridData<UsuarioDTO> parsePageToGridData(Page<Usuario> pageUsuario) {
    GridData<UsuarioDTO> gridDataUsuarioDTO = new GridData<>();
    List<Usuario> listUsuario = pageUsuario.getContent();
    long totalCount = pageUsuario.getTotalElements();
    gridDataUsuarioDTO.setItems(mapUsuarioToUsuarioDTO(listUsuario));
    gridDataUsuarioDTO.setTotalCount(totalCount);
    return gridDataUsuarioDTO;
  }

  /* Auth */

  public UsuarioDTO getCurrentUsuario() {

    Usuario usuario = UserContextHolder.getUser();

    UsuarioDTO usuarioDTO = new UsuarioDTO();
    usuarioDTO.setNombres(usuario.getNombres());
    usuarioDTO.setApellidos(usuario.getApellidos());
    usuarioDTO.setCorreo(usuario.getCorreo());
    usuarioDTO.setNick(usuario.getNick());

    List<RolDTO> listRolDTO = usuario.getRoles().stream().map(rol -> {

      RolDTO rolDTO = new RolDTO();
      rolDTO.setEtiqueta(rol.getRuta());

      return rolDTO;
    }).collect(Collectors.toList());

    usuarioDTO.setRoles(listRolDTO);

    return usuarioDTO;
  }

  @Transactional
  public ResponseMensaje changePassword(ChangePasswordRequest changePasswordRequest) {

    Usuario usuario = changePasswordRequest.getUsuario();

    String encodedPassword = encodePassword(changePasswordRequest.getPassword());
    usuario.setPasswordHash(encodedPassword);

    usuarioRepository.save(usuario);

    return new ResponseMensaje(200, "Constraseña actualizada");
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public Usuario getUsuarioByCorreo(String correo) {

    return usuarioRepository.findByCorreo(correo).orElse(null);
  }

  public static String generarPassword() {
    String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    String cadena = "";
    for (int x = 0; x < 10; x++) {
      int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
      char caracterAleatorio = banco.charAt(indiceAleatorio);
      cadena += caracterAleatorio;
    }
    return cadena;
  }

  private static int numeroAleatorioEnRango(int minimo, int maximo) {
    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
  }
}
