package com.chang.springboot.jpa.entityBuilder;

import java.util.function.Consumer;

public interface EntityValidator<E> {

    @SuppressWarnings("unchecked")
    default <V extends EntityBuilder<E>> V validate(ValidateFunction<V> validateFunction) {
        validateFunction.validate((V) this);
        return (V)this;
    }


}
