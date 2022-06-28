package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;

public interface ICommentMovieService {
    CommentDto createComment(CommentDto  comment);
}
