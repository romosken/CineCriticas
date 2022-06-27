package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;

public interface ILikeDislikeCommentService {

    boolean commentExists(int commentId);
    CommentBean likeComment(int commentId, boolean add);
    CommentBean dislikeComment(int commentId, boolean add);
}
