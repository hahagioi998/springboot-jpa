package com.chang.springboot.jpa.entityBuilder;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.function.Supplier;

public interface EntityUpdator<E> extends EntityValidator<E> {

    default E update(Supplier<E> supplier) {
        return update(supplier.get());
    }

    default E update(E entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        modelMapper.map(this, entity);
        return entity;
    }
}
