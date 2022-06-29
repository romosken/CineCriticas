package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment.port.ILikeDislikeMarkRepeatedCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeMarkRepeatedCommentImpl implements ILikeDislikeMarkRepeatedComment {

    private final ILikeDislikeMarkRepeatedCommentService iLikeDislikeMarkRepeatedCommentService;

    private static final String LIKE_SUCCESS = "Success adding/removing like to comment!";
    private static final String DISLIKE_SUCCESS = "Success adding/removing dislike to comment!";

    private static final String REPEATED_SUCCESS = "Success adding/removing repeated to comment!";


    @Override
    public LikeDislikeMarkRepeatedCommentResponse likeComment(LikeDislikeMarkRepeatedCommentRequest request) {
        int commentId = request.getCommentId();
        iLikeDislikeMarkRepeatedCommentService.likeComment(commentId, request.isAdd());
        return LikeDislikeMarkRepeatedCommentResponse.builder().response(LIKE_SUCCESS).build();
    }

    @Override
    public LikeDislikeMarkRepeatedCommentResponse dislikeComment(LikeDislikeMarkRepeatedCommentRequest request) {
        int commentId = request.getCommentId();
        iLikeDislikeMarkRepeatedCommentService.dislikeComment(commentId, request.isAdd());
        return LikeDislikeMarkRepeatedCommentResponse.builder().response(DISLIKE_SUCCESS).build();
    }

    @Override
    public LikeDislikeMarkRepeatedCommentResponse markCommentRepeated(LikeDislikeMarkRepeatedCommentRequest request) {
        int commentId = request.getCommentId();
        iLikeDislikeMarkRepeatedCommentService.markCommentRepeated(commentId, request.isAdd());
        return LikeDislikeMarkRepeatedCommentResponse.builder().response(REPEATED_SUCCESS).build();
    }


}
