package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.CommentNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UserNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.CommentRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.port.ICommentMovieService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentMovieServiceImpl implements ICommentMovieService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final IOmdb iOmdb;
    private static final String USER_NOT_EXISTS = "The user does not exist!";

    private static final String COMMENT_NOT_EXISTS = "The comment referenced does not exist!";




    @Override
    public CommentBean createComment(CommentDto comment) {

        validateUserAndMovie(comment.getUsername(), comment.getMovieId());
        var zeroTest = Integer.valueOf(0);
        var commentBean = CommentBean.builder()
                .username(comment.getUsername())
                .movieId(comment.getMovieId())
                .text(comment.getText())
                .commentReference(zeroTest.equals(comment.getCommentReference()) ? null : getComment(comment.getCommentReference()))
                .commentReply(zeroTest.equals(comment.getCommentReply()) ? null : getComment(comment.getCommentReply()))
                .build();
        return commentRepository.save(commentBean);
    }

    private void validateUserAndMovie(String username, String movieId) {

        if (userRepository.findById(username).isEmpty())
            throw new UserNotFoundException(USER_NOT_EXISTS);
        iOmdb.getMovie(OmdbRequest.builder().movieId(movieId).build());
    }

    private CommentBean getComment(int id) {

        var comment = commentRepository.findById(id);
        if (comment.isEmpty())
            throw new CommentNotFoundException(COMMENT_NOT_EXISTS);
        return comment.get();
    }
}
