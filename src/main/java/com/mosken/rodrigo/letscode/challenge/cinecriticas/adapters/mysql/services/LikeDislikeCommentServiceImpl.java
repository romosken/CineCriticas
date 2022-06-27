package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.CommentNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.CommentRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.port.ILikeDislikeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeCommentServiceImpl implements ILikeDislikeCommentService {

    private final CommentRepository commentRepository;
    private static final String COMMENT_NOT_EXISTS = "The comment referenced does not exist!";


    @Override
    public boolean commentExists(int commentId) {

        return commentRepository.existsById(commentId);
    }

    @Override
    public CommentBean likeComment(int commentId, boolean add) {

        var comment = getComment(commentId);
        comment.setLikes(add ? Integer.sum(comment.getLikes(), 1) : comment.getLikes() - 1);
        return commentRepository.save(comment);
    }

    @Override
    public CommentBean dislikeComment(int commentId, boolean add) {

        var comment = getComment(commentId);
        comment.setDislikes(add ? Integer.sum(comment.getDislikes(), 1) : comment.getDislikes() - 1);
        return commentRepository.save(comment);
    }

    private CommentBean getComment(int id) {

        var comment = commentRepository.findById(id);
        if (comment.isEmpty())
            throw new CommentNotFoundException(COMMENT_NOT_EXISTS);
        return comment.get();
    }
}
