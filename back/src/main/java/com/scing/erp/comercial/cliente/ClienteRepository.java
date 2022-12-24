package com.scing.erp.comercial.cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  public List<Cliente> findAllByOrderByEliminadoAsc();
  public List<Cliente> findAllByEliminado(boolean eliminado);
  Optional<Cliente> findByRuc(Long ruc);
}
