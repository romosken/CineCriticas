package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.CommentRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.port.IDeleteCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentServiceImpl implements IDeleteCommentService {

    private final CommentRepository commentRepository;
    private static final String DELETE_SUCCESS = "The comment was successfully deleted!";

    @Override
    public boolean commentExists(int commentId) {

        return commentRepository.existsById(commentId);
    }

    @Override
    public String deleteComment(int commentId) {

        commentRepository.deleteById(commentId);
        return DELETE_SUCCESS;
    }



}
