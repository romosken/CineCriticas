package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.CommentBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain.RatingBean;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.CommentRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.RatingRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.port.IReturnMovieCommentsRatingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReturnMovieCommentsRatingsServiceImpl implements IReturnMovieCommentsRatingsService {

    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    @Override
    public List<CommentDto> getComments(String movieId) {
        var comments = commentRepository.findByMovieId(movieId);
        return buildCommentDtoList(comments);
    }

    @Override
    public List<RatingDto> getRatings(String movieId) {
        var ratings = ratingRepository.findByMovieId(movieId);
        return buildRatingDtoList(ratings);
    }

    private List<CommentDto> buildCommentDtoList(List<CommentBean> comments) {
        return comments.stream().map(comment -> CommentDto.builder()
                .id(comment.getId())
                .username(comment.getUsername())
                .movieId(comment.getMovieId())
                .text(comment.getText())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .commentReference(
                        Objects.isNull(comment.getCommentReference()) ? 0 : comment.getCommentReference().getId()
                )
                .commentReply(
                        Objects.isNull(comment.getCommentReply()) ? 0 : comment.getCommentReply().getId()
                )
                .repeated(comment.isRepeated())
                .build()
        ).collect(Collectors.toList());
    }

    private List<RatingDto> buildRatingDtoList(List<RatingBean> ratings) {
        return ratings.stream().map(rating -> RatingDto.builder()
                .id(rating.getId())
                .username(rating.getUsername())
                .movieId(rating.getMovieId())
                .rating(rating.getRating())
                .build()
        ).collect(Collectors.toList());
    }
}
