package com.scing.erp.sistema.usuario.specification;

import com.scing.erp.sistema.entity.specification.SearchCriteria;
import com.scing.erp.sistema.usuario.Usuario;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UsuarioSpecification implements Specification<Usuario> {

  private static final long serialVersionUID = -4415234963138321694L;

  private transient SearchCriteria criteria;

  public UsuarioSpecification(SearchCriteria searchCriteria) {
    this.criteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    if (!criteria.getOperation().equalsIgnoreCase(":")) {
      return null;
    }

    return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
  }
}
