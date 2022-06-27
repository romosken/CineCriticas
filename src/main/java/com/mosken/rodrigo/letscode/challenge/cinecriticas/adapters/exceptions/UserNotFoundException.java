package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions;


import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(String msg){super(msg);}
}
