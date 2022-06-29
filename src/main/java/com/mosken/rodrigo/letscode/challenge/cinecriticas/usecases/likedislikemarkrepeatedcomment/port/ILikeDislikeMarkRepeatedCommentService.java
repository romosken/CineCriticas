package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment.port;

public interface ILikeDislikeMarkRepeatedCommentService {

    boolean commentExists(int commentId);
    boolean likeComment(int commentId, boolean add);
    boolean dislikeComment(int commentId, boolean add);
    boolean markCommentRepeated(int commentId, boolean add);
}
