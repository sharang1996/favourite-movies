package dev.sharanggupta.favouritemovies.dao;

import dev.sharanggupta.favouritemovies.entity.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class MovieDao implements Dao<Movie> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MovieDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Movie get(String id) {
        try {
            return namedParameterJdbcTemplate.queryForObject("select * from movies where id = :id",
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Movie movie, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Movie movie) {
        throw new UnsupportedOperationException();
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
