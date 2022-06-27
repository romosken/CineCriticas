package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.port;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;

public interface ICommentMovieService {
    CommentBean createComment(CommentDto  comment);
}
