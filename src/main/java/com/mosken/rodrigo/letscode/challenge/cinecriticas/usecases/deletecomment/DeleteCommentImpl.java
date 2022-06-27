package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.exception.CommentNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.port.IDeleteCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentImpl implements IDeleteComment {
    
    private final IDeleteCommentService iDeleteCommentService;
    private static final String COMMENT_NOT_EXISTS = "The comment does not exists!";


    @Override
    public DeleteCommentResponse deleteComment(DeleteCommentRequest request) {
        int commentId = request.getId();
        if (!iDeleteCommentService.commentExists(commentId))
            throw new CommentNotFoundException(COMMENT_NOT_EXISTS);
       var response =  iDeleteCommentService.deleteComment(commentId);
        return DeleteCommentResponse.builder().response(response).build();
    }


}
