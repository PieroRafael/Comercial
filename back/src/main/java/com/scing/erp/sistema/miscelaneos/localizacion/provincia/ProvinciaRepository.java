package com.scing.erp.sistema.miscelaneos.localizacion.provincia;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.scing.erp.sistema.miscelaneos.localizacion.departamento.Departamento;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
  Optional<Provincia> findByNombre(String nombre);
  public List<Provincia> findAllByDepartamentoOrderByEliminadoAsc(Departamento departamento);
}
