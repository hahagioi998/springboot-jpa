package com.chang.springboot.jpa.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public interface
Specificationable<T> extends Specification<T> {

    Specification<T> specification();

    @Override
    default Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return  specification().toPredicate(root, query, criteriaBuilder);
    }

    default Specification<T> where(Specification<T> specification) {
        return Specification.where(specification);
    }

    default <V> Specification<T> eq(String column, V value) {
        return (root, q, builder) -> builder.equal(root.get(column), value);
    }

    default <V> Specification<T> eq(String column, Optional<V> optional) {
        return optional.map(v -> eq(column, v))
                .orElseGet(EmptySpecification::new);
    }

    default <V> Specification<T> ne(String column, V value) {
        return (root, q, builder) -> builder.notEqual(root.get(column), value);
    }

    default <V> Specification<T> ne(String column, Optional<V> optional) {
        return optional.map(v -> ne(column, v))
                .orElseGet(EmptySpecification::new);
    }

    default <V extends Comparable<? super V>> Specification<T> gt(String column, V value) {
        return (root, q, builder) -> builder.greaterThan(root.get(column), value);
    }

    default <V extends Comparable<? super V>> Specification<T> gt(String column, Optional<V> optional) {
        return optional.map(v -> gt(column, v))
                .orElseGet(EmptySpecification::new);
    }

    default <V extends Comparable<? super V>> Specification<T> lt(String column, V value) {
        return (root, q, builder) -> builder.lessThan(root.get(column), value);
    }

    default <V extends Comparable<? super V>> Specification<T> lt(String column, Optional<V> optional) {
        return optional.map(v -> lt(column, v))
                .orElseGet(EmptySpecification::new);
    }

    class EmptySpecification implements Specification {
        @Override
        public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {
            return builder.conjunction();
        }
    }
}
