package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;

import java.util.List;

public interface IReturnMovieCommentsRatingsService {
    List<CommentDto> getComments(String movieId);
    List<RatingDto> getRatings(String movieId);
}
