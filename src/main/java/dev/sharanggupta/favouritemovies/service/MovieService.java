package dev.sharanggupta.favouritemovies.service;

import dev.sharanggupta.favouritemovies.dao.Dao;
import dev.sharanggupta.favouritemovies.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MovieService {
    Dao<Movie> movieDao;

    public MovieService(Dao<Movie> movieDao) {
        this.movieDao = movieDao;
    }

    public Movie getMovie(String id){
        return movieDao.get(id).orElseThrow(()-> new NoSuchElementException(String.format("Movie with id: %s not found", id)));
    }
}
