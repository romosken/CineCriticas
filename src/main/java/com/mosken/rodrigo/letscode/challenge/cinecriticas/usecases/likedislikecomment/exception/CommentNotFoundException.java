package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.exception;


import java.util.NoSuchElementException;

public class CommentNotFoundException extends NoSuchElementException {
    public CommentNotFoundException(String msg){super(msg);}
}
