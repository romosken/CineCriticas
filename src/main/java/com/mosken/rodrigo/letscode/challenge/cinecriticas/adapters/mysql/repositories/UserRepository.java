package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserBean, String> {
}
