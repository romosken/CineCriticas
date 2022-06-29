package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieReturnModel {

    private String id;
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
    private List<MovieRatingReturnModel> ratings;
    private String metascore;
    @JsonProperty("imdb_rating")
    private String imdbRating;
    @JsonProperty("imdb_votes")
    private String imdbVotes;
    private String type;
    private String dvd;
    @JsonProperty("box_office")
    private String boxOffice;
    private String production;
    private String website;

    @Data
    @Builder
    public static class MovieRatingReturnModel {

        private String source;
        private String value;

    }
}
