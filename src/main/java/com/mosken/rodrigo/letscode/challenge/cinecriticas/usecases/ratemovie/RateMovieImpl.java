package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RatingBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Rating;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.AddXpRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.IAddXp;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.exception.DuplicateRatingException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.exception.InvalidRatingRangeException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.port.IRateMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateMovieImpl implements IRateMovie {

    private final IRateMovieService iRateMovieService;
    private final IAddXp iAddXp;
    private static final String CREATION_SUCCESS = "Success inserting new rating!";
    private static final String RATING_ALREADY_EXISTS = "Rating already exists!";
    private static final String RATING_OUT_OF_RANGE = "Rating must be between 0 and 100!";

    @Override
    public RateMovieResponse rateMovie(RateMovieRequest request) {
        validateRequest(request);

        var responseRateMovie = iRateMovieService.createRate(buildRatingDto(request));
        validateResponse(responseRateMovie);

        iAddXp.addXpToUser(AddXpRequest.builder().username(request.getUsername()).xpToAdd(1).build());
        return buildRateMovieResponseSuccess();
    }

    private void validateRequest(RateMovieRequest request) {
        if (iRateMovieService.ratingExists(request.getUsername(), request.getMovieId()))
            throw new DuplicateRatingException(RATING_ALREADY_EXISTS);
        var rating = request.getRating();
        if (rating>100 || rating<0)
            throw new InvalidRatingRangeException(RATING_OUT_OF_RANGE);
    }

    private RatingDto buildRatingDto(RateMovieRequest request) {
        return RatingDto.builder()
                .username(request.getUsername())
                .movieId(request.getMovieId())
                .rating(request.getRating())
                .build();
    }

    private Rating validateResponse(RatingBean response) {
        return Rating.builder()
                .id(response.getId())
                .username(response.getUsername())
                .movieId(response.getMovieId())
                .rate(response.getRating())
                .build();
    }

    private RateMovieResponse buildRateMovieResponseSuccess() {
        return RateMovieResponse.builder().response(CREATION_SUCCESS).build();
    }


}
