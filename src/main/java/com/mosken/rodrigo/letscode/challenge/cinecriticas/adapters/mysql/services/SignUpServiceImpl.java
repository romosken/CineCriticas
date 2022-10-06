package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RoleBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.port.ISignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements ISignUpService {

    private final UserRepository userRepository;


    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto createUser(UserDto user) throws DataIntegrityViolationException {
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
