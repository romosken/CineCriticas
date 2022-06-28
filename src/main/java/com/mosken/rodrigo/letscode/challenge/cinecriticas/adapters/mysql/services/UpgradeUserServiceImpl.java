package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UserNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RoleBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.port.ISignUpService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.port.IUpgradeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpgradeUserServiceImpl implements IUpgradeUserService {

    private final UserRepository userRepository;
    private static final String USER_NOT_EXISTS = "The user does not exist!";



    @Override
    public UserDto getUser(String username) {
        var userResponse = userRepository.findById(username);
        if (userResponse.isEmpty())
            throw new UserNotFoundException(USER_NOT_EXISTS);
        return buildUserDto(userResponse.get());
    }

    @Override
    public UserDto saveUser(UserDto user) {
        var bean = UserBean.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .xp(user.getXp())
                .role(RoleBean.builder().name(user.getRole()).build())
                .build();
        UserBean userSaved = userRepository.save(bean);
        return buildUserDto(userSaved);
    }

    private UserDto buildUserDto(UserBean userSaved) {
        return UserDto.builder()
                .username(userSaved.getUsername())
                .email(userSaved.getEmail())
                .password(userSaved.getPassword())
                .xp(userSaved.getXp())
                .role(userSaved.getRole().getName())
                .build();
    }
}
