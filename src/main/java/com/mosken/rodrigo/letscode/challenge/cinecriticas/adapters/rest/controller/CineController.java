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
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.ILikeDislikeComment;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.LikeDislikeCommentRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.likedislikecomment.LikeDislikeCommentResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.IRateMovie;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.RateMovieRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.ratemovie.RateMovieResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.ISignIn;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.SignInRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/cinecriticas/v1")
@RequiredArgsConstructor
public class CineController {

    //TODO adicionar logs
    private final IOmdb iOmdb;
    private final ISignIn iSignIn;
    private final IRateMovie iRateMovie;
    private final ICommentMovie iCommentMovie;
    private final IDeleteComment iDeleteComment;
    private final ILikeDislikeComment iLikeDislikeComment;
    private static final String ID_AND_TITLE_NULL = "At least one argument is required! (id or title)";


    private final IAddXp iAddXp;

    @GetMapping("/search/movie")
    public ResponseEntity<OmdbResponse> getMovie(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "year", required = false) String year
    ) {

        if ((Objects.isNull(title) || title.isBlank()) && (Objects.isNull(id) || id.isBlank()))
            throw new InvalidResourceException(ID_AND_TITLE_NULL);
        var response = iOmdb.getMovie(OmdbRequest.builder().movieTitle(title).movieId(id).movieYear(year).build());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/user/signin")
    public ResponseEntity<SignInResponse> insertUser(
            @RequestBody UserDto user
    ) {
        var response = iSignIn.signIn(SignInRequest.builder()
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
        var response = iDeleteComment.deleteComment(DeleteCommentRequest.builder()
                .id(id)
                .build());
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/comment/like/{id}")
    public ResponseEntity<LikeDislikeCommentResponse> likeComment(
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        var response = iLikeDislikeComment.likeComment(LikeDislikeCommentRequest.builder()
                .commentId(id)
                .add(add)
                .build());
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/comment/dislike/{id}")
    public ResponseEntity<LikeDislikeCommentResponse> dislikeComment(
            @PathVariable int id,
            @RequestParam(name = "add", defaultValue = "true", required = false) boolean add
    ) {
        var response = iLikeDislikeComment.dislikeComment(LikeDislikeCommentRequest.builder()
                .commentId(id)
                .add(add)
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


