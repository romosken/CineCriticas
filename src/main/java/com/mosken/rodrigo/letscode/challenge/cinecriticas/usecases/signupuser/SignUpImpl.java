package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Role;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.User;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.exception.DuplicateUserException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.port.ISignUpService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
@RequiredArgsConstructor
public class SignUpImpl implements ISignUp {

    private final ISignUpService iSignUpService;
    private static final String USER_ALREADY_EXISTS = "Username already exists!";
    private static final String EMAIL_ALREADY_EXISTS = "Email already exists!";

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        validateRequest(request);
        var validatedUser = buildUser(request);
        var response = iSignUpService.createUser(buildUserDto(validatedUser));
        return buildSignUpResponse(response);

    }

    private void validateRequest(SignUpRequest request) {
        if (iSignUpService.userExists(request.getUsername()))
            throw new DuplicateUserException(USER_ALREADY_EXISTS);
        if (iSignUpService.emailExists(request.getEmail()))
            throw new DuplicateUserException(EMAIL_ALREADY_EXISTS);
    }

    private SignUpResponse buildSignUpResponse(UserDto response) {
        return SignUpResponse.builder()
                .username(response.getUsername())
                .email(response.getEmail())
                .xp(response.getXp())
                .role(response.getRole())
                .build();
    }

    private User buildUser(SignUpRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encryptPassword(request.getPassword()))
                .xp(0)
                .role(Role.builder().name(ERole.LEITOR).build())
                .build();

    }

    private UserDto buildUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .xp(user.getXp())
                .role(user.getRole().getName())
                .build();
    }
    @SneakyThrows
    private String encryptPassword(String password) {
        var algorithm = MessageDigest.getInstance("SHA-256");
        var messageDigest = algorithm.digest(password.getBytes(StandardCharsets.UTF_8));
        var hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
       return hexString.toString();
    }
}
