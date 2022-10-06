package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.port;

public interface IDeleteCommentService {

    boolean commentExists(int commentId);
    String deleteComment(int commentId);

}
