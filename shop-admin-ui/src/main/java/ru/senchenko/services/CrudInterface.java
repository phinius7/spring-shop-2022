package ru.senchenko.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CrudInterface<T> {

    void create(T dao) throws IOException;

    List<T> readAll();

    Optional<T> readById(Integer id);

    void delete(Integer id);
}
