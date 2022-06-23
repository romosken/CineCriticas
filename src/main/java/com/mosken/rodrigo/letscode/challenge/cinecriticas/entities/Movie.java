package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.MovieException;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
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
    private List<Rating> ratings;
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
                 List<Rating> ratings,
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

    public void setRated(String rated) {
//        validateAttribute(rated, "RATED");
        this.rated = rated;
    }

    public void setReleased(String released) {
        validateAttribute(released, "RELEASED");
        this.released = released;
    }

    public void setRuntime(String runtime) {
//        validateAttribute(runtime, "RUNTIME");
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
//        validateAttribute(genre, "GENRE");
        this.genre = genre;
    }

    public void setDirector(String director) {
//        validateAttribute(director, "DIRECTOR");
        this.director = director;
    }

    public void setWriter(String writer) {
//        validateAttribute(writer, "WRITER");
        this.writer = writer;
    }

    public void setActors(String actors) {
//        validateAttribute(actors, "ACTORS");
        this.actors = actors;
    }

    public void setPlot(String plot) {
//        validateAttribute(plot, "PLOT");
        this.plot = plot;
    }

    public void setLanguage(String language) {
//        validateAttribute(language, "LANGUAGE");
        this.language = language;
    }

    public void setCountry(String country) {
//        validateAttribute(country, "CONTRY");
        this.country = country;
    }

    public void setAwards(String awards) {
//        validateAttribute(awards, "AWARDS");
        this.awards = awards;
    }

    public void setPoster(String poster) {
//        validateAttribute(poster, "POSTER");
        this.poster = poster;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setMetascore(String metascore) {
//        validateAttribute(metascore, "METASCORE");
        this.metascore = metascore;
    }

    public void setImdbRating(String imdbRating) {
//        validateAttribute(imdbRating, "IMDB_RATING");
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
//        validateAttribute(imdbVotes, "IMDB_VOTES");
        this.imdbVotes = imdbVotes;
    }

    public void setImdbID(String imdbID) {
        validateAttribute(imdbID, "IMDB_ID");
        this.imdbID = imdbID;
    }

    public void setType(String type) {
        validateAttribute(type, "TYPE");
        this.type = type;
    }

    public void setDvd(String dvd) {
//        validateAttribute(dvd, "DVD");
        this.dvd = dvd;
    }

    public void setBoxOffice(String boxOffice) {
//        validateAttribute(boxOffice, "BOX_OFFICE");
        this.boxOffice = boxOffice;
    }

    public void setProduction(String production) {
//        validateAttribute(production, "PRODUCTION");
        this.production = production;
    }

    public void setWebsite(String website) {
//        validateAttribute(website, "WEBSITE");
        this.website = website;
    }



    private void validateAttribute(String attribute, String attributeName) {
        if (Objects.isNull(attribute) || attribute.isBlank())
            throw new MovieException(attributeName + " cannot be null or empty!");

    }

    public static class MovieBuilder{
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
