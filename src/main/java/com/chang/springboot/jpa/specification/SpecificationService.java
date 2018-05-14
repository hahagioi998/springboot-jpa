package com.chang.springboot.jpa.specification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SpecificationService<T, ID> {

    JpaSpecificationRepository<T, ID> getRepository();

    default Optional<T> findOne(ID id) {
        return getRepository().findById(id);
    }

    default Optional<T> findOne(Specification<T> s) {
        return getRepository().findOne(s);
    }

    default List<T> findAll(Specification<T> s) {
        return getRepository().findAll(s);
    }

    default Page<T> findAll(Specification<T> s, Pageable pageable) {
        return getRepository().findAll(s, pageable);
    }

    default long count(Specification<T> s) {
        return getRepository().count(s);
    }
}
