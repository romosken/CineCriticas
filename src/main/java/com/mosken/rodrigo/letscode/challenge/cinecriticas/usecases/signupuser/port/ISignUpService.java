package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;

public interface ISignUpService {

    boolean userExists(String username);
    UserDto createUser(UserDto user);
}
