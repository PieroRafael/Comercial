package com.scing.erp.sistema.miscelaneos.banco;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
  Optional<Banco> findByNombre(String nombre);
  public List<Banco> findAllByOrderByEliminadoAsc();
}
