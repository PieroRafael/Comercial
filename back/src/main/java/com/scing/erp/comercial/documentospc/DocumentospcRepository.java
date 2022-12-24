package com.scing.erp.comercial.documentospc;

import java.util.List;
import java.util.Optional;

import com.scing.erp.comercial.seguimientospc.Seguimientospc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentospcRepository extends JpaRepository<Documentospc, Long> {
  public List<Documentospc> findAllBySeguimientospc(Seguimientospc seguimientospc);
  Optional<Documentospc> findByNombreAndSeguimientospc(String nombre, Seguimientospc seguimientospc);
}
