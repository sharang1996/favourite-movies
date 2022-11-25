package dev.sharanggupta.favouritemovies.dao;

import dev.sharanggupta.favouritemovies.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements Dao<User> {
    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException();
    }
}
