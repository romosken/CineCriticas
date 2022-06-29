package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.exception;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;

public class InvalidRatingRangeException extends InvalidResourceException {
    public InvalidRatingRangeException(String msg){super(msg);}
}
