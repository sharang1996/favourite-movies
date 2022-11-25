package dev.sharanggupta.favouritemovies.controller;

import dev.sharanggupta.favouritemovies.entity.Movie;
import dev.sharanggupta.favouritemovies.service.MovieService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable String id) {
        return movieService.getMovie(id);
    }
}
