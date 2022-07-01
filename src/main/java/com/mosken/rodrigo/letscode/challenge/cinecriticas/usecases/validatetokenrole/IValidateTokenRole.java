package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole;

public interface IValidateTokenRole {

    void verifyTokenAndUserRole(ValidateTokenRoleRequest request);
    void verifyToken(ValidateTokenRoleRequest request);
    void verifyUserRole(ValidateTokenRoleRequest request);
}
