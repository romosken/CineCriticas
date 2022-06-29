package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.RatingNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UserNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RatingBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.RatingRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.port.IRateMovieService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateMovieServiceImpl implements IRateMovieService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final IOmdb iOmdb;
    private static final String USER_NOT_EXISTS = "The user does not exist!";
    private static final String RATING_NOT_EXISTS = "The rating does not exist!";

    private void validateUserAndMovie(String username, String movieId) {
        if (!userRepository.existsById(username))
            throw new UserNotFoundException(USER_NOT_EXISTS);
        iOmdb.getMovie(OmdbRequest.builder().movieId(movieId).build());
    }

    @Override
    public boolean ratingExists(String username, String movieId) {
        validateUserAndMovie(username, movieId);
        return ratingRepository.existsByMovieIdAndUsername(movieId, username);
    }

    @Override
    public RatingDto createRate(RatingDto rate) {
        var rateBean = RatingBean.builder()
                .username(rate.getUsername())
                .movieId(rate.getMovieId())
                .rating(rate.getRating())
                .build();
        return buildRatingDto(ratingRepository.save(rateBean));
    }

    @Override
    public RatingDto updateRate(RatingDto rate) {
        //this validation is redundant to decouple the application
        var rateBean = ratingRepository.findByMovieIdAndUsername(rate.getMovieId(), rate.getUsername());
        if (rateBean.isEmpty())
            throw new RatingNotFoundException(RATING_NOT_EXISTS);
        var rateSave = rateBean.get();
        rateSave.setRating(rate.getRating());
        return buildRatingDto(ratingRepository.save(rateSave));
    }

    private RatingDto buildRatingDto(RatingBean rateSaved) {
        return RatingDto.builder()
                .id(rateSaved.getId())
                .username(rateSaved.getUsername())
                .movieId(rateSaved.getMovieId())
                .rating(rateSaved.getRating())
                .build();
    }
}
