package com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

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
