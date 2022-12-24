package com.scing.erp.sistema.miscelaneos.localizacion.distrito;

import java.util.List;
import java.util.Optional;

import com.scing.erp.sistema.miscelaneos.localizacion.provincia.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Long> {
  Optional<Distrito> findByNombre(String nombre);
  public List<Distrito> findAllByProvinciaOrderByEliminadoAsc(Provincia provincia);
}
