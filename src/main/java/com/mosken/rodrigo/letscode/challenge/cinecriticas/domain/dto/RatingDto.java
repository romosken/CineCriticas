package com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    @JsonProperty("movie_id")
    private String movieId;
    private String username;
    private int rating;
}
