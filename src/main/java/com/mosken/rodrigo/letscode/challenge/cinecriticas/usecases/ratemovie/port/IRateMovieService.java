package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RatingBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;

public interface IRateMovieService {

    boolean ratingExists(String username, String movieId);
    RatingBean createRate(RatingDto rate);
}
