package com.scing.erp.comercial.seguimientospc;

import java.util.List;
import com.scing.erp.comercial.spc.Spc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientospcRepository extends JpaRepository<Seguimientospc, Long> {
  public List<Seguimientospc> findAllBySpc(Spc spc);
}
