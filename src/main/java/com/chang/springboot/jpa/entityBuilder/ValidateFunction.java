package com.chang.springboot.jpa.entityBuilder;

@FunctionalInterface
public interface ValidateFunction<T> {
    void validate(T t);
}
