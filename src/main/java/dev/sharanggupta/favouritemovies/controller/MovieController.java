package dev.sharanggupta.favouritemovies.controller;

import dev.sharanggupta.favouritemovies.entity.Movie;
import dev.sharanggupta.favouritemovies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> get(@PathVariable String id) {
    return new ResponseEntity<>(movieService.getMovie(id), HttpStatus.OK);
  }

  @PostMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Movie> post(@RequestBody Movie movie) {
    return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.OK);
  }
}
