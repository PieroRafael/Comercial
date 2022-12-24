package com.scing.erp.sistema.miscelaneos.localizacion.departamento;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.scing.erp.sistema.miscelaneos.localizacion.pais.Pais;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
  Optional<Departamento> findByNombre(String nombre);
  public List<Departamento> findAllByPaisOrderByEliminadoAsc(Pais pais);
  @Query(value = "SELECT * FROM departamento ORDER BY departamento.eliminado ASC , departamento.nombre ASC", nativeQuery = true)
  public List<Departamento> findAllOrdenado();
}
