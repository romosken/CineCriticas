package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.CommentRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.exception.CommentNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment.port.ILikeDislikeMarkRepeatedCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeDislikeMarkRepeatedCommentServiceImpl implements ILikeDislikeMarkRepeatedCommentService {

    private final CommentRepository commentRepository;
    private static final String COMMENT_NOT_EXISTS = "The comment referenced does not exist!";


    @Override
    public boolean commentExists(int commentId) {

        return commentRepository.existsById(commentId);
    }

    @Override
    public boolean likeComment(int commentId, boolean add) {

        var comment = getComment(commentId);
        comment.setLikes(add ? Integer.sum(comment.getLikes(), 1) : comment.getLikes() - 1);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean dislikeComment(int commentId, boolean add) {

        var comment = getComment(commentId);
        comment.setDislikes(add ? Integer.sum(comment.getDislikes(), 1) : comment.getDislikes() - 1);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean markCommentRepeated(int commentId, boolean add) {

        var comment = getComment(commentId);
        comment.setRepeated(add);
        commentRepository.save(comment);
        return true;
    }

    private CommentBean getComment(int id) {
        var commentResponse = commentRepository.findById(id);
        if (commentResponse.isEmpty())
            throw new CommentNotFoundException(COMMENT_NOT_EXISTS);
        return commentResponse.get();
    }
}
