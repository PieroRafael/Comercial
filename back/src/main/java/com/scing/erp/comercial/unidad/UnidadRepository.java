package com.scing.erp.comercial.unidad;

import java.util.List;
import java.util.Optional;
import com.scing.erp.comercial.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Long> {
  public List<Unidad> findAllByOrderByEliminadoAsc();
  public List<Unidad> findAllByClienteAndEliminadoOrderByNombreAsc(Cliente cliente, boolean eliminado);
  public List<Unidad> findAllByClienteOrderByEliminadoAsc(Cliente cliente);
  Optional<Unidad> findByNombre(String nombre);
  public List<Unidad> findAllByEliminado(boolean eliminado);
}
