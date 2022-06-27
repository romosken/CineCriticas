package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentBean, Integer> {
}
