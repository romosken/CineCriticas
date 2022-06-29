package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RateMovieRequest {
    private String username;
    private String movieId;
    private int rating;
}
