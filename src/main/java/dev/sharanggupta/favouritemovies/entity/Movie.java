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
        this.id = id;
        this.title = title;
        this.plot = plot;
        this.poster = poster;
        this.released = released;
        this.rating = rating;
        this.runtime = runtime;
        this.genre = genre;
    }

    public boolean isEmpty(){
        return StringUtils.isEmpty(title)
                &&StringUtils.isEmpty(plot)
                &&StringUtils.isEmpty(poster)
                &&StringUtils.isEmpty(released)
                &&StringUtils.isEmpty(rating)
                &&StringUtils.isEmpty(runtime)
                &&StringUtils.isEmpty(genre);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating){
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
