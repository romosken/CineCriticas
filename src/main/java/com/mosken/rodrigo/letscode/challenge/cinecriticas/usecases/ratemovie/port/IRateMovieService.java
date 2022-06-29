package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;

public interface IRateMovieService {

    boolean ratingExists(String username, String movieId);
    RatingDto createRate(RatingDto rate);
    RatingDto updateRate(RatingDto rate);
}
