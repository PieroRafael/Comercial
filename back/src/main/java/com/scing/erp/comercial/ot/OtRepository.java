package com.scing.erp.comercial.ot;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtRepository extends JpaRepository<Ot, Long> {
  public List<Ot> findAllByOrderByEliminadoAsc();
  public List<Ot> findAllByEliminadoAndTipoproyecto(boolean eliminado, boolean tipoproyecto);
  public List<Ot> findAllByOtprincipal(Long otprincipal);
  Optional<Ot> findByCodigo(String codigo);
}
