package com.scing.erp.sistema.usuario;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository
    extends PagingAndSortingRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

  Optional<Usuario> findByCorreo(String correo);
  public List<Usuario> findAllByEliminadoAndVendedorOrderByApellidosAsc(boolean eliminado, boolean vendedor);
}
