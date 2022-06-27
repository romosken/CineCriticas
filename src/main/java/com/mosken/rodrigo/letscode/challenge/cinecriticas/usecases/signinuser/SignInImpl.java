package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.exception.DuplicateUserException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.port.ISignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInImpl implements ISignIn {

    private final ISignInService iSignInService;
    private static final String USER_ALREADY_EXISTS = "Username already exists!";
    private static final String EMAIL_ALREADY_EXISTS = "Email already exists!";

    @Override
    public SignInResponse signIn(SignInRequest request) {
        //TODO: adicionar logs
        if (iSignInService.userExists(request.getUsername()))
            throw new DuplicateUserException(USER_ALREADY_EXISTS);
        var validatedUser = buildUser(request);
        //TODO: adicionar segurança nas senhas
        try {
            var response = iSignInService.createUser(validatedUser);
            return buildSignInResponse(response);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUserException(EMAIL_ALREADY_EXISTS);
        }

    }

    private SignInResponse buildSignInResponse(UserBean response) {
        return SignInResponse.builder()
                .username(response.getUsername())
                .email(response.getEmail())
                .password(response.getPassword())
                .xp(response.getXp())
                .role(response.getRole().getName())
                .build();
    }

    private UserDto buildUser(SignInRequest request) {
        return UserDto.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .xp(0)
                .role(ERole.LEITOR)
                .build();

    }
}
