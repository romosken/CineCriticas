package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment;

public interface ILikeDislikeMarkRepeatedComment {

    LikeDislikeMarkRepeatedCommentResponse likeComment(LikeDislikeMarkRepeatedCommentRequest request);
    LikeDislikeMarkRepeatedCommentResponse dislikeComment(LikeDislikeMarkRepeatedCommentRequest request);
    LikeDislikeMarkRepeatedCommentResponse markCommentRepeated(LikeDislikeMarkRepeatedCommentRequest request);
}
