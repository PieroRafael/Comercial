package com.scing.erp.sistema.miscelaneos.area;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
  public List<Area> findAllByOrderByEliminadoAscNombreAsc();
  public List<Area> findAllByEliminadoOrderByNombreAsc(boolean eliminado);
  Area findByNombre(String nombre);
}
