package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RoleBean;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleBean, String> {
    Optional<RoleBean> findByName(ERole name);
}
