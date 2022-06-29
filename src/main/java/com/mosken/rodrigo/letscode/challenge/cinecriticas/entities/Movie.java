package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.MovieException;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Movie {

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private List<MovieRating> ratings;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String type;
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;

    public Movie(String title,
                 String year,
                 String rated,
                 String released,
                 String runtime,
                 String genre,
                 String director,
                 String writer,
                 String actors,
                 String plot,
                 String language,
                 String country,
                 String awards,
                 String poster,
                 List<MovieRating> ratings,
                 String metascore,
                 String imdbRating,
                 String imdbVotes,
                 String imdbID,
                 String type,
                 String dvd,
                 String boxOffice,
                 String production,
                 String website) {
        setTitle(title);
        setYear(year);
        setRated(rated);
        setReleased(released);
        setRuntime(runtime);
        setGenre(genre);
        setDirector(director);
        setWriter(writer);
        setActors(actors);
        setPlot(plot);
        setLanguage(language);
        setCountry(country);
        setAwards(awards);
        setPoster(poster);
        setRatings(ratings);
        setMetascore(metascore);
        setImdbRating(imdbRating);
        setImdbVotes(imdbVotes);
        setImdbID(imdbID);
        setType(type);
        setDvd(dvd);
        setBoxOffice(boxOffice);
        setProduction(production);
        setWebsite(website);
    }

    public void setTitle(String title) {
        validateAttribute(title, "TITLE");
        this.title = title;
    }

    public void setYear(String year) {
        validateAttribute(year, "YEAR");
        this.year = year;
    }


    public void setReleased(String released) {
        validateAttribute(released, "RELEASED");
        this.released = released;
    }


    private void validateAttribute(String attribute, String attributeName) {
        if (Objects.isNull(attribute) || attribute.isBlank())
            throw new MovieException(attributeName + " cannot be null or empty!");

    }

    public static class MovieBuilder {
        public Movie build() {
            return new Movie(
                    title,
                    year,
                    rated,
                    released,
                    runtime,
                    genre,
                    director,
                    writer,
                    actors,
                    plot,
                    language,
                    country,
                    awards,
                    poster,
                    ratings,
                    metascore,
                    imdbRating,
                    imdbVotes,
                    imdbID,
                    type,
                    dvd,
                    boxOffice,
                    production,
                    website
            );
        }
    }


}
