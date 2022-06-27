package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.exception;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.DuplicateEntryException;

public class DuplicateRatingException extends DuplicateEntryException {
    public DuplicateRatingException(String msg){super(msg);}
}
