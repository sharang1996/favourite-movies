package dev.sharanggupta.favouritemovies.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dao<T> {
    T get(String id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
