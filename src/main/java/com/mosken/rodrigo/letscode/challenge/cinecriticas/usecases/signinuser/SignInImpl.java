package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.User;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.UserException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.port.ISignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInImpl implements ISignIn{

    private final ISignInService iSignInService;
    private static final String USER_ALREADY_EXISTS = "Username already exists in database!";
    @Override
    public SignInResponse signIn(SignInRequest request) {
        //TODO adicionar logs
        if(iSignInService.userExists(request.getUsername()))
            throw new UserException(USER_ALREADY_EXISTS);
        var validatedUser = buildUser(request);
        //TODO adicionar segurança nas senhas
        var response = iSignInService.createUser(validatedUser);
        return buildSignInResponse(response);
    }

    private SignInResponse buildSignInResponse(User response) {
        return SignInResponse.builder()
                .username(response.getUsername())
                .email(response.getEmail())
                .password(response.getPassword())
                .xp(response.getXp())
                .role(response.getRole().toString())
                .build();
    }

    private User buildUser(SignInRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .xp(0)
                .role(iSignInService.getRole(ERole.LEITOR))
                .build();

    }
}
