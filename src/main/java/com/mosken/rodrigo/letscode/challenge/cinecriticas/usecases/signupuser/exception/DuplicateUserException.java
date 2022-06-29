package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.exception;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.DuplicateEntryException;

public class DuplicateUserException extends DuplicateEntryException {
    public DuplicateUserException(String msg){super(msg);}
}
