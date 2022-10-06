package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ValidateTokenRoleRequest {

    private String token;
    private String username;
    private List<ERole> requiredRoles;
}
