package dev.sharanggupta.favouritemovies.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    String id;
    @JsonProperty("Title")
    String title;
    @JsonProperty("Plot")
    String plot;
    @JsonProperty("Poster")
    String poster;
    @JsonProperty("Released")
    String released;
    @JsonProperty("imdbRating")
    String rating;
    @JsonProperty("Runtime")
    String runtime;
    @JsonProperty("Genre")
    String genre;

    public Movie() {
    }

    public Movie(String id, String title, String plot, String poster, String released, String rating, String runtime, String genre) {
        this.id = sanitise(id);
        this.title = sanitise(title);
        this.plot = sanitise(plot);
        this.poster = sanitise(poster);
        this.released = sanitise(released);
        this.rating = sanitise(rating);
        this.runtime = sanitise(runtime);
        this.genre = sanitise(genre);
    }

    public static boolean isEmpty(Movie movie) {
        return StringUtils.isEmpty(movie.getTitle())
                && StringUtils.isEmpty(movie.getPlot())
                && StringUtils.isEmpty(movie.getPoster())
                && StringUtils.isEmpty(movie.getReleased())
                && StringUtils.isEmpty(movie.getRating())
                && StringUtils.isEmpty(movie.getRuntime())
                && StringUtils.isEmpty(movie.getGenre());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = sanitise(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = sanitise(title);
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = sanitise(plot);
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = sanitise(poster);
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = sanitise(released);
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = sanitise(rating);
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = sanitise(runtime);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = sanitise(genre);
    }

    private static String sanitise(String field) {
        return StringUtils.trim(field);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id) && title.equals(movie.title) && plot.equals(movie.plot) && poster.equals(movie.poster) && released.equals(movie.released) && rating.equals(movie.rating) && runtime.equals(movie.runtime) && genre.equals(movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, plot, poster, released, rating, runtime, genre);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", plot='" + plot + '\'' +
                ", poster='" + poster + '\'' +
                ", released='" + released + '\'' +
                ", rating='" + rating + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}