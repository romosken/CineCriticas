package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.controller;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UnauthorizedException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.client.IAuthorizationApi;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.models.LogInResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.CommentMovieRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.CommentMovieResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.commentmovie.ICommentMovie;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.DeleteCommentRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.DeleteCommentResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.deletecomment.IDeleteComment;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment.ILikeDislikeMarkRepeatedComment;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment.LikeDislikeMarkRepeatedCommentRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikemarkrepeatedcomment.LikeDislikeMarkRepeatedCommentResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.IRateMovie;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.RateMovieRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.RateMovieResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.IReturnMovieCommentsRatings;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.ReturnMovieCommentsRatingsRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.ReturnMovieCommentsRatingsResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.ISignUp;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.SignUpRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.SignUpResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.IUpgradeUser;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.UpgradeUserRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.UpgradeUserResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.IValidateTokenRole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.ValidateTokenRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/cinecriticas/v1")
@RequiredArgsConstructor
public class CineController {

    private final IReturnMovieCommentsRatings iReturnMovieCommentsRatings;
    private final ISignUp iSignUp;
    private final IRateMovie iRateMovie;
    private final ICommentMovie iCommentMovie;
    private final IDeleteComment iDeleteComment;
    private final ILikeDislikeMarkRepeatedComment iLikeDislikeMarkRepeatedComment;
    private final IUpgradeUser iUpgradeUser;
    private final IAuthorizationApi iAuthorizationApi;
    private final IValidateTokenRole iValidateTokenRole;
    private static final String ID_AND_TITLE_NULL = "At least one argument is required! (id or title)";

    @GetMapping("/search/movie")
    public ResponseEntity<ReturnMovieCommentsRatingsResponse> getMovie(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "year", required = false) String year
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.LEITOR,
                        ERole.BASICO,
                        ERole.AVANCADO,
                        ERole.MODERADOR
                ).collect(Collectors.toList()));

        if ((Objects.isNull(title) || title.isBlank()) && (Objects.isNull(id) || id.isBlank()))
            throw new InvalidResourceException(ID_AND_TITLE_NULL);
        var response = iReturnMovieCommentsRatings.getMoviesCommentsRatings(
                ReturnMovieCommentsRatingsRequest.builder()
                        .movieTitle(title)
                        .movieId(id)
                        .movieYear(year)
                        .build());
        return ResponseEntity.ok(response);

    }


    @PostMapping("/user/signup")
    public ResponseEntity<SignUpResponse> insertUser(
            @RequestBody UserDto user
    ) {
        var response = iSignUp.signUp(SignUpRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build());
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/user/login")
    public ResponseEntity<LogInResponse> insertUser(
            @RequestHeader String username,
            @RequestHeader String password
    ) {
        try{
        return ResponseEntity.ok(iAuthorizationApi.logIn(username, password));
    }catch(Exception e){
            throw new UnauthorizedException(e.getMessage());
        }
    }


    @PostMapping("/rating/add")
    public ResponseEntity<RateMovieResponse> insertRating(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody RatingDto ratingDto
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.LEITOR,
                        ERole.BASICO,
                        ERole.AVANCADO,
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iRateMovie.rateMovie(RateMovieRequest.builder()
                .username(ratingDto.getUsername())
                .movieId(ratingDto.getMovieId())
                .rating(ratingDto.getRating())
                .build());
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/comment/add")
    public ResponseEntity<CommentMovieResponse> insertComment(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody CommentDto commentDto
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.BASICO,
                        ERole.AVANCADO,
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iCommentMovie.commentMovie(CommentMovieRequest.builder()
                .username(commentDto.getUsername())
                .movieId(commentDto.getMovieId())
                .text(commentDto.getText())
                .commentReference(commentDto.getCommentReference())
                .commentReply(commentDto.getCommentReply())
                .build());
        return ResponseEntity.status(201).body(response);
    }


    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<DeleteCommentResponse> deleteComment(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable int id
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iDeleteComment.deleteComment(DeleteCommentRequest.builder()
                .id(id)
                .build());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/comment/like/{id}")
    public ResponseEntity<LikeDislikeMarkRepeatedCommentResponse> likeComment(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.AVANCADO,
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iLikeDislikeMarkRepeatedComment.likeComment(
                LikeDislikeMarkRepeatedCommentRequest.builder()
                        .commentId(id)
                        .add(add)
                        .build());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/comment/dislike/{id}")
    public ResponseEntity<LikeDislikeMarkRepeatedCommentResponse> dislikeComment(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.AVANCADO,
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iLikeDislikeMarkRepeatedComment.dislikeComment(
                LikeDislikeMarkRepeatedCommentRequest.builder()
                        .commentId(id)
                        .add(add)
                        .build());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/comment/markasrepeated/{id}")
    public ResponseEntity<LikeDislikeMarkRepeatedCommentResponse> markCommentRepeated(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iLikeDislikeMarkRepeatedComment.markCommentRepeated(
                LikeDislikeMarkRepeatedCommentRequest.builder()
                        .commentId(id)
                        .add(add)
                        .build());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/user/turnmoderator/{username}")
    public ResponseEntity<UpgradeUserResponse> turnUserModerator(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String username
    ) {
        validateTokenUser(
                token,
                Stream.of(
                        ERole.MODERADOR
                ).collect(Collectors.toList()));
        var response = iUpgradeUser.turnUserModerator(UpgradeUserRequest.builder()
                .username(username)
                .build());
        return ResponseEntity.ok(response);
    }


    private void validateTokenUser(String token, List<ERole> roles) {
        iValidateTokenRole.verifyTokenAndUserRole(ValidateTokenRoleRequest.builder()
                .token(token)
                .requiredRoles(roles)
                .build());
    }

}


