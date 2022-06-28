package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.exception.DuplicateUserException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.port.ISignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpImpl implements ISignUp {

    private final ISignUpService iSignUpService;
    private static final String USER_ALREADY_EXISTS = "Username already exists!";
    private static final String EMAIL_ALREADY_EXISTS = "Email already exists!";

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        //TODO: adicionar logs
        if (iSignUpService.userExists(request.getUsername()))
            throw new DuplicateUserException(USER_ALREADY_EXISTS);
        var validatedUser = buildUser(request);
        //TODO: adicionar segurança nas senhas
        try {
            var response = iSignUpService.createUser(validatedUser);
            return buildSignUpResponse(response);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUserException(EMAIL_ALREADY_EXISTS);
        }

    }

    private SignUpResponse buildSignUpResponse(UserDto response) {
        return SignUpResponse.builder()
                .username(response.getUsername())
                .email(response.getEmail())
                .password(response.getPassword())
                .xp(response.getXp())
                .role(response.getRole())
                .build();
    }

    private UserDto buildUser(SignUpRequest request) {
        return UserDto.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .xp(0)
                .role(ERole.LEITOR)
                .build();

    }
}
