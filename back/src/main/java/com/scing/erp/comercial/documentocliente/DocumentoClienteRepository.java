package com.scing.erp.comercial.documentocliente;

import java.util.List;
import com.google.common.base.Optional;
import com.scing.erp.comercial.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoclienteRepository extends JpaRepository<Documentocliente, Long> {
  Optional<Documentocliente> findByNombreAndCliente(String nombre, Cliente cliente);
  public List<Documentocliente> findAllByClienteOrderByEliminadoAsc(Cliente cliente);
}
