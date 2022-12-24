package com.scing.erp.sistema.usuario.specification.builder;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.scing.erp.sistema.entity.specification.SearchCriteria;
import com.scing.erp.sistema.usuario.Usuario;
import com.scing.erp.sistema.usuario.UsuarioGridFilter;
import com.scing.erp.sistema.usuario.specification.UsuarioSpecification;

@Component
public class UsuarioSpecificationBuilder {

  private List<SearchCriteria> params;

  public UsuarioSpecificationBuilder() {
    params = new ArrayList<>();
  }

  private UsuarioSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  private void initParams(UsuarioGridFilter filter) {
    if (filter.getFilterBynombres() != null) {
      with("nombres", ":", filter.getFilterBynombres());
    }
    if (filter.getFilterByapellidos() != null) {
      with("apellidos", ":", filter.getFilterByapellidos());
    }
    if (filter.getFilterBycorreo() != null) {
      with("correo", ":", filter.getFilterBycorreo());
    }
    if (filter.getFilterBynick() != null) {
      with("nick", ":", filter.getFilterBynick());
    }
  }

  public Optional<Specification<Usuario>> build(UsuarioGridFilter filter) {
    initParams(filter);

    if (params.size() == 0) {
      return Optional.empty();
    }

    Specification<Usuario> result = new UsuarioSpecification(params.get(0));

    for (int i = 1; i < params.size(); i++) {
      result = Specification.where(result).and(new UsuarioSpecification(params.get(i)));
    }

    return Optional.of(result);
  }
}
