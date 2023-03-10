package dev.sharanggupta.favouritemovies.service;

import dev.sharanggupta.favouritemovies.dao.Dao;
import dev.sharanggupta.favouritemovies.entity.Movie;
import dev.sharanggupta.favouritemovies.exception.MovieValidationException;
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
  private final Dao<Movie> movieDao;
  private final RestTemplate restTemplate;

  @Value("${omdb.api.key}")
  String apiKey;

  @Value("${omdb.api.host}")
  String apiHost;

  @Value("${omdb.api.scheme}")
  String apiScheme;

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

  public Movie addMovie(Movie movie) {
    validateMovie(movie);
    movieDao.save(movie);
    return movie;
  }

  private void validateMovie(Movie movie) {
    if (movieDao.contains(movie.getId()))
      throw new MovieValidationException(
          "Movie with id %s already exists in the database".formatted(movie.getId()));
    if (!movie.equals(getMovieFromApi(movie.getId())))
      throw new MovieValidationException(
          "Movie received does not match api response for id %s".formatted(movie.getId()));
  }

  private Movie getMovieFromApi(String id) {
    MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
    queryParameters.add("apikey", apiKey);
    queryParameters.add("i", id);
    queryParameters.add("plot", "full");

    URI uri =
        UriComponentsBuilder.newInstance()
            .scheme(apiScheme)
            .host(apiHost)
            .path("/")
            .queryParams(queryParameters)
            .build()
            .toUri();
    Movie movie = restTemplate.getForEntity(uri, Movie.class).getBody();
    if (movie == null || Movie.isEmpty(movie))
      throw new NoSuchElementException(
          "Could not find movie with id %s, host=%s".formatted(id, uri.getHost()));
    movie.setId(id);
    return movie;
  }
}
