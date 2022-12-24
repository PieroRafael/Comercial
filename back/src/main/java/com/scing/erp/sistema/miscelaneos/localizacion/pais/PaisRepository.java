package com.scing.erp.sistema.miscelaneos.localizacion.pais;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
  Optional<Pais> findByNombre(String nombre);
  public List<Pais> findAllByOrderByEliminadoAsc();
}
