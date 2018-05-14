package com.chang.springboot.jpa.entityBuilder;

import java.util.function.Consumer;

public interface EntityBuilder<T> {

    T build();

    default T build(Consumer<T> complete) {
        T t =  build();
        complete.accept(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    default <V extends EntityBuilder<T>> V validate(ValidateFunction<V> validateFunction) {
        validateFunction.validate((V) this);
        return (V)this;
    }
}
