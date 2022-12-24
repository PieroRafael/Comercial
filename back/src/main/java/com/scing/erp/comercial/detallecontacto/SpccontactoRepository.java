package com.scing.erp.comercial.detallecontacto;

import java.util.List;
import com.scing.erp.comercial.spc.Spc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpccontactoRepository extends JpaRepository<Spccontacto, Long> {
  List<Spccontacto> findBySpc(Spc spc);
  Long deleteBySpc(Spc spc);
}
