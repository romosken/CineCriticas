package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UserNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.client.IAuthorizationApi;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.models.ParseTokenResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.port.IValidateTokenService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.port.ParsedToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateTokenServiceImpl implements IValidateTokenService {

    private final UserRepository userRepository;
    private final IAuthorizationApi authorizationApi;
    private static final String USER_NOT_FOUND = "User not found!";
    private static final String INVALID_TOKEN = "Token invalid!";


    @Override
    public ParsedToken parseToken(String token) {
        try {
            var parsedTokenResponse = authorizationApi.parseToken(token);
            return buildParsedToken(parsedTokenResponse);
        } catch (Exception e) {
            throw new InvalidResourceException(INVALID_TOKEN);
        }
    }

    private ParsedToken buildParsedToken(ParseTokenResponse parsedTokenResponse) {
        return ParsedToken.builder()
                .tokenId(parsedTokenResponse.getTokenId())
                .subject(parsedTokenResponse.getSubject())
                .build();
    }

    @Override
    public UserDto getUser(String username) {
        var user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        return buildUserDto(user);
    }

    private UserDto buildUserDto(UserBean user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .xp(user.getXp())
                .role(user.getRole().getName())
                .build();
    }
}
