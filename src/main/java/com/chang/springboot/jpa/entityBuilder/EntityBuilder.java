package com.chang.springboot.jpa.entityBuilder;

import java.util.function.Consumer;

public interface EntityBuilder<E> extends EntityValidator<E> {

    E build();

    default E build(Consumer<E> complete) {
        E t =  build();
        complete.accept(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    default <V extends EntityBuilder<E>> V validate(ValidateFunction<V> validateFunction) {
        validateFunction.validate((V) this);
        return (V)this;
    }

    class LazyBuilder<E> implements EntityBuilder<E> {


        @Override
        public E build() {
            return null;
        }
    }
}
