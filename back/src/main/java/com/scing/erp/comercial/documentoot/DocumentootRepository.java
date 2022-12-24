package com.scing.erp.comercial.documentoot;

import java.util.List;
import java.util.Optional;
import com.scing.erp.comercial.ot.Ot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentootRepository extends JpaRepository<Documentoot, Long> {
  Optional<Documentoot> findByNombreAndOt(String nombre, Ot ot);
  public List<Documentoot> findAllByOtOrderByEliminadoAsc(Ot ot);
}
