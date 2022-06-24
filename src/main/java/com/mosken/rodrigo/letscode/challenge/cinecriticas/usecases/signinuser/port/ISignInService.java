package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Role;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.User;

public interface ISignInService {
    Role getRole(ERole role);

    boolean userExists(String username );
    User createUser(User user);
}
