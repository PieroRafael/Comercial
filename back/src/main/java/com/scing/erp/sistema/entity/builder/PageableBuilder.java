package com.scing.erp.sistema.entity.builder;

import com.scing.erp.sistema.entity.enums.SortOrder;
import com.scing.erp.sistema.usuario.BaseFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableBuilder {

    public Pageable build(BaseFilter filter, String tabla) {

        int pageNumber = filter.getPageNumber() - 1;
        int pageSize = filter.getPageSize();
        String sortBy = filter.getSortBy();
        String sortField = sortBy;

        Pageable pageable;
        SortOrder sortOrder = filter.getOrderBy() == null ? SortOrder.EMPTY
                                                       : SortOrder.valueOfIgnoreCase(filter.getOrderBy());
        switch (sortOrder) {
            case ASC:
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField));
                break;
            case DESC:
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).descending());
                break;
            case EMPTY:
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"+tabla).descending());
                break;
            default:
                throw new IllegalArgumentException("Wrong sort order");
        }
        return pageable;
    }
}
