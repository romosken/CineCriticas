package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LikeDislikeCommentRequest {

    private int commentId;
    private boolean add;
}
