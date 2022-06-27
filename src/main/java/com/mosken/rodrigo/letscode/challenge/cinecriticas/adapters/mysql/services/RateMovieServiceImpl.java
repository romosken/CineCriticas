package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

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

    private void validateUserAndMovie(String username, String movieId){
        if (userRepository.findById(username).isEmpty())
            throw new UserNotFoundException(USER_NOT_EXISTS);
        iOmdb.getMovie(OmdbRequest.builder().movieId(movieId).build());
    }
    @Override
    public boolean ratingExists(String username, String movieId) {
       validateUserAndMovie(username, movieId);
        return ratingRepository.existsByMovieIdAndUsername(movieId, username);
    }

    @Override
    public RatingBean createRate(RatingDto rate) {
        var rateBean = RatingBean.builder()
                .username(rate.getUsername())
                .movieId(rate.getMovieId())
                .rating(rate.getRating())
                .build();
        return ratingRepository.save(rateBean);
    }
}
