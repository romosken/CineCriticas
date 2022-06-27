package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentMovieResponse {
    private int id;
    @JsonProperty("movie_id")
    private String movieId;
    private String username;
    private String text;
    private int likes;
    private int dislikes;
    @JsonProperty("comment_reference")
    private int commentReference;
    @JsonProperty("comment_reply")
    private int commentReply;
    private boolean repeated;
}
