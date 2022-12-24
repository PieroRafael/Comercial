package com.scing.erp.comercial.contacto;

import java.util.List;
import java.util.Optional;
import com.scing.erp.comercial.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
  public List<Contacto> findAllByOrderByEliminadoAsc();
  public List<Contacto> findAllByClienteOrderByEliminadoAsc(Cliente cliente);
  Optional<Contacto> findByCorreo(String correo);
}
