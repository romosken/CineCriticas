package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.exceptions;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UnauthorizedException;

public class RoleUnauthorizedException extends UnauthorizedException {
    public RoleUnauthorizedException(String msg){super(msg);}
}
