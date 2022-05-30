package com.epam.esm.service.dto.converter;

public interface DtoEntityConverter<T, R> {
    T convertToDto(R object);

    R convertToEntity(T object);
}
