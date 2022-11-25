package dev.sharanggupta.favouritemovies.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Dao<T> {
    Optional<T> get(String id);

    boolean isPresent(String id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
