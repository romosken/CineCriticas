package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Comment;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.AddXpRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.IAddXp;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.port.ICommentMovieService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.IValidateTokenRole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.ValidateTokenRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommentMovieImpl implements ICommentMovie {

    private final ICommentMovieService iCommentMovieService;
    private final IValidateTokenRole iValidateTokenRole;
    private final IAddXp iAddXp;


    @Override
    public CommentMovieResponse commentMovie(CommentMovieRequest request) {

        var responseCommentMovie = iCommentMovieService.createComment(buildCommentDto(request));
        var entity = validateResponse(responseCommentMovie);

        iAddXp.addXpToUser(AddXpRequest.builder().username(request.getUsername()).xpToAdd(1).build());
        return buildCommentMovieResponse(entity);
    }

    private CommentDto buildCommentDto(CommentMovieRequest request) {
        int commentReference = request.getCommentReference();
        if (!Integer.valueOf(0).equals(commentReference))
            validateUser(request);
        return CommentDto.builder()
                .username(request.getUsername())
                .movieId(request.getMovieId())
                .text(request.getText())
                .commentReference(commentReference)
                .commentReply(request.getCommentReply())
                .build();
    }

    private void validateUser(CommentMovieRequest request) {
        var roles = Stream.of(ERole.AVANCADO, ERole.MODERADOR).collect(Collectors.toList());
        iValidateTokenRole.verifyUserRole(ValidateTokenRoleRequest.builder()
                .username(request.getUsername())
                .requiredRoles(roles)
                .build());
    }



    private Comment validateResponse(CommentDto response) {
        return Comment.builder()
                .id(response.getId())
                .username(response.getUsername())
                .movieId(response.getMovieId())
                .text(response.getText())
                .commentReference(response.getCommentReference())
                .commentReply(response.getCommentReply())
                .build();
    }

    private CommentMovieResponse buildCommentMovieResponse(Comment comment) {
        return CommentMovieResponse.builder()
                .id(comment.getId())
                .username(comment.getUsername())
                .movieId(comment.getMovieId())
                .text(comment.getText())
                .commentReference(comment.getCommentReference())
                .commentReply(comment.getCommentReply())
                .build();
    }


}
