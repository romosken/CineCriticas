package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RatingBean;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<RatingBean, Integer> {
    boolean existsByMovieIdAndUsername(String movieId, String username);
}
