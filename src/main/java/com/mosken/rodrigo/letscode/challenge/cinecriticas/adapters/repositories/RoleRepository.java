package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.repositories;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
