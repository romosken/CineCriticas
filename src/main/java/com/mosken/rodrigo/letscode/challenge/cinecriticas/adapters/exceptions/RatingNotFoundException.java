package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions;


import java.util.NoSuchElementException;

public class RatingNotFoundException extends NoSuchElementException {
    public RatingNotFoundException(String msg){super(msg);}
}
