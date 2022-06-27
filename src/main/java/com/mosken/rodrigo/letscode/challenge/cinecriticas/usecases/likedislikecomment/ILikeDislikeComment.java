package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment;

public interface ILikeDislikeComment {

    LikeDislikeCommentResponse likeComment(LikeDislikeCommentRequest request);
    LikeDislikeCommentResponse dislikeComment(LikeDislikeCommentRequest request);
}
