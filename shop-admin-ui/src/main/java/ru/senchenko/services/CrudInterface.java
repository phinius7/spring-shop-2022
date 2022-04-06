package ru.senchenko.services;

import java.util.List;
import java.util.Optional;

public interface CrudInterface<T> {

    void create(T dao);

    List<T> readAll();

    Optional<T> readById(Integer id);

    void delete(Integer id);
}
