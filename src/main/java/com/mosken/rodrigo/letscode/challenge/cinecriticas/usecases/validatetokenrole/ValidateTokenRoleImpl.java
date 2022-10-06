package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Role;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.User;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.exceptions.RoleUnauthorizedException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.port.IValidateTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateTokenRoleImpl implements IValidateTokenRole {

    private final IValidateTokenService iValidateTokenService;
    private static final String ROLE_UNAUTHORIZED = "User does not have access to this feature!";

    @Override
    public void verifyTokenAndUserRole(ValidateTokenRoleRequest request) {
        var parsedToken = iValidateTokenService.parseToken(request.getToken());
        var username = parsedToken.getSubject();
        var user = buildUserEntity(iValidateTokenService.getUser(username));

        validateRequiredRoleUserRole(request.getRequiredRoles(), user.getRole().getName());
    }

    @Override
    public void verifyToken(ValidateTokenRoleRequest request) {
        iValidateTokenService.parseToken(request.getToken());
    }

    @Override
    public void verifyUserRole(ValidateTokenRoleRequest request) {
        var user = buildUserEntity(iValidateTokenService.getUser(request.getUsername()));
        validateRequiredRoleUserRole(request.getRequiredRoles(), user.getRole().getName());
    }

    private User buildUserEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .xp(userDto.getXp())
                .role(Role.builder().name(userDto.getRole()).build())
                .build();

    }

    private void validateRequiredRoleUserRole(List<ERole> requiredRole, ERole role) {
        if (!requiredRole.contains(role))
            throw new RoleUnauthorizedException(ROLE_UNAUTHORIZED);
    }
}
