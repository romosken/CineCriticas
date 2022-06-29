package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;

public interface IUpgradeUserService {
    UserDto getUser(String username);
    UserDto saveUser(UserDto user);

}
