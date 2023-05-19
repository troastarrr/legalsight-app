package com.legalsight.mapper;

public interface BaseMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

}
