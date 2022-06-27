package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions;


import java.util.NoSuchElementException;

public class CommentNotFoundException extends NoSuchElementException {
    public CommentNotFoundException(String msg){super(msg);}
}
