package dev.sharanggupta.favouritemovies.dao;

import dev.sharanggupta.favouritemovies.entity.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDao implements Dao<Movie>{

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MovieDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Movie> get(String id) {

        Movie movie;
        Optional<Movie> optionalMovie = Optional.empty();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try{
            movie = namedParameterJdbcTemplate.queryForObject("select * from movies where id = :id", namedParameters, new MovieRowMapper());
            optionalMovie = Optional.ofNullable(movie);
            System.out.println(movie);
        }catch (Exception e){
            e.printStackTrace();
        }
        return optionalMovie;
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
