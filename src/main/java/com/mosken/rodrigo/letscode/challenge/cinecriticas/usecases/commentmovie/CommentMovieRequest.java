package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentMovieRequest {
    private String movieId;
    private String username;
    private String text;
    private int likes;
    private int dislikes;
    private int commentReference;
    private int commentReply;
}
