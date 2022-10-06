package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Rating;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.AddXpRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.IAddXp;
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
    private static final String RATING_OUT_OF_RANGE = "Rating must be between 0 and 100!";

    @Override
    public RateMovieResponse rateMovie(RateMovieRequest request) {
        validateRequest(request);
        var ratingDto = buildRatingDto(request);
        var response = iRateMovieService.ratingExists(request.getUsername(), request.getMovieId()) ?
                executeRateUpdate(ratingDto) :
                executeRateCreation(ratingDto);

        validateResponse(response);
        return buildRateMovieResponseSuccess();
    }

    private void validateRequest(RateMovieRequest request) {
        var rating = request.getRating();
        if (rating > 100 || rating < 0)
            throw new InvalidRatingRangeException(RATING_OUT_OF_RANGE);
    }

    private RatingDto buildRatingDto(RateMovieRequest request) {
        return RatingDto.builder()
                .username(request.getUsername())
                .movieId(request.getMovieId())
                .rating(request.getRating())
                .build();
    }

    private RatingDto executeRateUpdate(RatingDto request) {
        return iRateMovieService.updateRate(request);
    }

    private RatingDto executeRateCreation(RatingDto request) {
        iAddXp.addXpToUser(AddXpRequest.builder().username(request.getUsername()).xpToAdd(1).build());
        return iRateMovieService.createRate(request);
    }

    private Rating validateResponse(RatingDto response) {
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
