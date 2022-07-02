package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;

public interface IValidateTokenService {

    ParsedToken parseToken(String token);
    UserDto getUser(String username);
}
