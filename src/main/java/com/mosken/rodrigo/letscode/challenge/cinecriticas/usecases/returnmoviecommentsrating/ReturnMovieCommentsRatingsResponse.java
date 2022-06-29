package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models.CommentReturnModel;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models.MovieReturnModel;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models.RatingReturnModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ReturnMovieCommentsRatingsResponse {

    @JsonProperty("movie_data")
    private MovieReturnModel movieData;
    private List<CommentReturnModel> comments;
    private List<RatingReturnModel> ratings;

}
