package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RoleBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.port.ISignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements ISignInService {

    private final UserRepository userRepository;


    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public UserBean createUser(UserDto user) throws DataIntegrityViolationException {
            var bean = UserBean.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .xp(user.getXp())
                    .role(RoleBean.builder().name(user.getRole()).build())
                    .build();
            return userRepository.save(bean);

    }
}
