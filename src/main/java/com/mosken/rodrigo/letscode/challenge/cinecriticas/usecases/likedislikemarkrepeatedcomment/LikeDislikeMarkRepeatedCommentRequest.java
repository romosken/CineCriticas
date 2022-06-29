package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LikeDislikeMarkRepeatedCommentRequest {

    private int commentId;
    private boolean add;
}
