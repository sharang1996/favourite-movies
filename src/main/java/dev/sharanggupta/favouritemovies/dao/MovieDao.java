package dev.sharanggupta.favouritemovies.dao;

import dev.sharanggupta.favouritemovies.entity.Movie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class MovieDao implements Dao<Movie> {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MovieDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Movie> get(String id) {

        Movie movie;
        Optional<Movie> optionalMovie;
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            movie = namedParameterJdbcTemplate.queryForObject("select * from movies where id = :id", namedParameters, new MovieRowMapper());
            optionalMovie = Optional.ofNullable(movie);
            System.out.println(movie);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        return optionalMovie;
    }

    @Override
    public boolean isPresent(String id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        int count = namedParameterJdbcTemplate.queryForObject("select count(*) from movies where id = :id", namedParameters, Integer.class);
        return count > 0;
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }

    @Override
    public void save(Movie movie) {

    }

    @Override
    public void update(Movie movie, String[] params) {

    }

    @Override
    public void delete(Movie movie) {

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
