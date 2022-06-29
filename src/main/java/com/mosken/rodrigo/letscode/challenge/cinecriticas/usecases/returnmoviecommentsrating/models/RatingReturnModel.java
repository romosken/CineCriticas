package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RatingReturnModel {

    private int id;
    @JsonProperty("movie_id")
    private String movieId;
    private String username;
    private int rating;
}
