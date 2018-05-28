package com.chang.springboot.jpa.entityBuilder;

import java.util.function.Consumer;

public interface EntityBuilder<E> extends EntityValidator<E> {

    E build();

    default E build(Consumer<E> complete) {
        E t =  build();
        complete.accept(t);
        return t;
    }
}
