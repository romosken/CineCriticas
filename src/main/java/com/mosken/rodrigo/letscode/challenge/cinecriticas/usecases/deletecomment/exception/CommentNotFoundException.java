package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.exception;


import java.util.NoSuchElementException;

public class CommentNotFoundException extends NoSuchElementException {
    public CommentNotFoundException(String msg){super(msg);}
}
