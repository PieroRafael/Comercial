package com.scing.erp.comercial.spc;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpcRepository extends JpaRepository<Spc, Long> {
  public List<Spc> findAllByOrderByEliminadoAsc();
  Optional<Spc> findByCodigo(String codigo);
  @Query (value = "select * from spc left join ot on spc.idspc = ot.idspc where ot.idspc is null and spc.eliminado = false" , nativeQuery = true)
  public List<Spc> findAllByEliminado();
}
