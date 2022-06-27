package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;

public interface ISignInService {

    boolean userExists(String username);
    UserBean createUser(UserDto user);
}
