package com.chang.springboot.jpa.specification;

import org.springframework.data.domain.Pageable;

public interface PageSpecificationable<T> extends Specificationable<T> {

    Pageable pageable();
}
