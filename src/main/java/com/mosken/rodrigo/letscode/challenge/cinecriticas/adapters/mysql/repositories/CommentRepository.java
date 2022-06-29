package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentBean, Integer> {
    List<CommentBean> findByMovieId(String movieId);
}
