package com.scing.erp.sistema.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
  Rol findByEtiqueta(String etiqueta);
}
