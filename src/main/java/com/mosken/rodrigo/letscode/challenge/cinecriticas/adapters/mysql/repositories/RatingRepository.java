package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RatingBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends CrudRepository<RatingBean, Integer> {
    boolean existsByMovieIdAndUsername(String movieId, String username);
    Optional<RatingBean> findByMovieIdAndUsername(String movieId, String username);
    List<RatingBean> findByMovieId(String movieId);
}
