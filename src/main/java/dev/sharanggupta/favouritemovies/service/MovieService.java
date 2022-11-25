package dev.sharanggupta.favouritemovies.service;

import dev.sharanggupta.favouritemovies.dao.Dao;
import dev.sharanggupta.favouritemovies.entity.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@Service
public class MovieService {
    Dao<Movie> movieDao;
    private final RestTemplate restTemplate;

    @Value("${omdb.api.key}")
    String apiKey;

    @Value("${omdb.api.host}")
    String apiHost;

    public MovieService(Dao<Movie> movieDao, RestTemplate restTemplate) {
        this.movieDao = movieDao;
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(String id) {
        try {
            return movieDao.get(id);
        } catch (NoSuchElementException e) {
            return getMovieFromApi(id);
        }
    }

    private Movie getMovieFromApi(String id) {
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.add("apikey", apiKey);
        queryParameters.add("i", id);
        queryParameters.add("plot", "full");

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(apiHost)
                .path("/")
                .queryParams(queryParameters)
                .build()
                .toUri();
        Movie movie = restTemplate.getForEntity(uri, Movie.class).getBody();
        if(movie == null) throw new NoSuchElementException("Could not find movie with id %s".formatted(id));
        movie.setId(id);
        return movie;
    }
}
