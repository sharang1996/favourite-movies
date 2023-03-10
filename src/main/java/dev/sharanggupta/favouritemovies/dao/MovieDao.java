package dev.sharanggupta.favouritemovies.dao;

import com.google.common.collect.ImmutableMap;
import dev.sharanggupta.favouritemovies.entity.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class MovieDao implements Dao<Movie> {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public MovieDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Movie get(String id) {
    try {
      return namedParameterJdbcTemplate.queryForObject(
          "select * from movies where id = :id",
          new MapSqlParameterSource().addValue("id", id),
          new MovieRowMapper());
    } catch (DataAccessException e) {
      throw new NoSuchElementException("Could not find movie with id %s".formatted(id));
    }
  }

  @Override
  public List<Movie> getAll() {
    return new ArrayList<>();
  }

  @Override
  public void save(Movie movie) {
    Map<String, String> parameters = generateQueryParameters(movie);
    String sql =
        "INSERT INTO movies(id, title, plot, poster, released, rating, runtime, genre) "
            + "VALUES(:id, :title, :plot, :poster, :released, :rating, :runtime, :genre)";

    namedParameterJdbcTemplate.update(sql, parameters);
  }

  @Override
  public void update(Movie movie, String[] params) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(Movie movie) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean contains(String id) {
    try {
      namedParameterJdbcTemplate.queryForObject(
          "select * from movies where id = :id",
          new MapSqlParameterSource().addValue("id", id),
          new MovieRowMapper());
    } catch (DataAccessException e) {
      return false;
    }
    return true;
  }

  private Map<String, String> generateQueryParameters(Movie movie) {
    return ImmutableMap.<String, String>builder()
        .put("id", movie.getId())
        .put("title", movie.getTitle())
        .put("plot", movie.getPlot())
        .put("poster", movie.getPoster())
        .put("released", movie.getReleased())
        .put("rating", movie.getRating())
        .put("runtime", movie.getRuntime())
        .put("genre", movie.getGenre())
        .build();
  }

  public static class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
      Movie movie = new Movie();

      movie.setId(rs.getString("id"));
      movie.setTitle(rs.getString("title"));
      movie.setGenre(rs.getString("genre"));
      movie.setPlot(rs.getString("plot"));
      movie.setPoster(rs.getString("poster"));
      movie.setRating(rs.getString("rating"));
      movie.setReleased(rs.getString("released"));
      movie.setRuntime(rs.getString("runtime"));

      return movie;
    }
  }
}
