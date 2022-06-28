package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.controller;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.IAddXp;
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
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.ISignUp;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.SignUpRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser.SignUpResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.IUpgradeUser;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.UpgradeUserRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.UpgradeUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/cinecriticas/v1")
@RequiredArgsConstructor
public class CineController {

    private final IOmdb iOmdb;
    private final ISignUp iSignUp;
    private final IRateMovie iRateMovie;
    private final ICommentMovie iCommentMovie;
    private final IDeleteComment iDeleteComment;
    private final ILikeDislikeMarkRepeatedComment iLikeDislikeMarkRepeatedComment;
    private final IUpgradeUser iUpgradeUser;
    private static final String ID_AND_TITLE_NULL = "At least one argument is required! (id or title)";


    private final IAddXp iAddXp;

    @GetMapping("/search/movie")
    public ResponseEntity<OmdbResponse> getMovie(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "year", required = false) String year
    ) {
        //TODO: puxar ratings e comentarios junto dos dados do filme
        if ((Objects.isNull(title) || title.isBlank()) && (Objects.isNull(id) || id.isBlank()))
            throw new InvalidResourceException(ID_AND_TITLE_NULL);
        var response = iOmdb.getMovie(OmdbRequest.builder()
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

    @PostMapping("/rating/add")
    public ResponseEntity<RateMovieResponse> insertRating(
            @RequestBody RatingDto ratingDto
    ) {
        var response = iRateMovie.rateMovie(RateMovieRequest.builder()
                .username(ratingDto.getUsername())
                .movieId(ratingDto.getMovieId())
                .rating(ratingDto.getRating())
                .build());
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/comment/add")
    public ResponseEntity<CommentMovieResponse> insertComment(
            @RequestBody CommentDto commentDto
    ) {
        //TODO: Apenas >BASICO, adendo que apenas >AVANCADO pode citar outro comentario
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
            @PathVariable int id
    ) {
        //TODO: Apenas MODERADOR
        var response = iDeleteComment.deleteComment(DeleteCommentRequest.builder()
                .id(id)
                .build());
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/comment/like/{id}")
    public ResponseEntity<LikeDislikeMarkRepeatedCommentResponse> likeComment(
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        //TODO: Apenas >AVANCADO
        var response = iLikeDislikeMarkRepeatedComment.likeComment(
                LikeDislikeMarkRepeatedCommentRequest.builder()
                        .commentId(id)
                        .add(add)
                        .build());
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/comment/dislike/{id}")
    public ResponseEntity<LikeDislikeMarkRepeatedCommentResponse> dislikeComment(
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        //TODO: Apenas >AVANCADO
        var response = iLikeDislikeMarkRepeatedComment.dislikeComment(
                LikeDislikeMarkRepeatedCommentRequest.builder()
                        .commentId(id)
                        .add(add)
                        .build());
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/comment/markasrepeated/{id}")
    public ResponseEntity<LikeDislikeMarkRepeatedCommentResponse> markCommentRepeated(
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        //TODO: Apenas MODERADOR
        var response = iLikeDislikeMarkRepeatedComment.markCommentRepeated(
                LikeDislikeMarkRepeatedCommentRequest.builder()
                        .commentId(id)
                        .add(add)
                        .build());
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/user/turnmoderator/{username}")
    public ResponseEntity<UpgradeUserResponse> turnUserModerator(
            @PathVariable String username
    ) {
        //TODO: Apenas MODERADOR
        var response = iUpgradeUser.turnUserModerator(UpgradeUserRequest.builder()
                .username(username)
                .build());
        return ResponseEntity.status(201).body(response);
    }

//    @GetMapping("/add/{xpToAdd}")
//    public ResponseEntity<AddXpResponse> addXp(
//            @PathVariable(name = "xpToAdd") int xp
//    ) {
//
//
//        return ResponseEntity.ok(iAddXp.addXpToUser(AddXpRequest.builder().xpToAdd(xp).username("romosken").build()));
//
//    }
}


