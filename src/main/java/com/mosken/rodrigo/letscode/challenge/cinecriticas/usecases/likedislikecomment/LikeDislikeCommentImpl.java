package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.exception.CommentNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.port.ILikeDislikeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeCommentImpl implements ILikeDislikeComment {

    private final ILikeDislikeCommentService iLikeDislikeCommentService;

    private static final String COMMENT_NOT_EXISTS = "The comment does not exists!";
    private static final String LIKE_SUCCESS = "Success adding/removing like to comment!";
    private static final String DISLIKE_SUCCESS = "Success adding/removing dislike to comment!";


    @Override
    public LikeDislikeCommentResponse likeComment(LikeDislikeCommentRequest request) {
        int commentId = request.getCommentId();
        validateComment(commentId);
        iLikeDislikeCommentService.likeComment(commentId, request.isAdd());
        return LikeDislikeCommentResponse.builder().response(LIKE_SUCCESS).build();
    }

    @Override
    public LikeDislikeCommentResponse dislikeComment(LikeDislikeCommentRequest request) {
        int commentId = request.getCommentId();
        validateComment(commentId);
        iLikeDislikeCommentService.dislikeComment(commentId, request.isAdd());
        return LikeDislikeCommentResponse.builder().response(DISLIKE_SUCCESS).build();
    }

    private void validateComment(int commentId) {
        if (!iLikeDislikeCommentService.commentExists(commentId))
            throw new CommentNotFoundException(COMMENT_NOT_EXISTS);
    }



}
