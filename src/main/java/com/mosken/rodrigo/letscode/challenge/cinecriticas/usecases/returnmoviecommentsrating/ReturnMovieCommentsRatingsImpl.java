package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.CommentDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto.RatingDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models.CommentReturnModel;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models.MovieReturnModel;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.models.RatingReturnModel;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnmoviecommentsrating.port.IReturnMovieCommentsRatingsService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReturnMovieCommentsRatingsImpl implements IReturnMovieCommentsRatings {

    private final IOmdb iOmdb;
    private final IReturnMovieCommentsRatingsService iReturnMovieCommentsRatingsService;

    @Override
    public ReturnMovieCommentsRatingsResponse getMoviesCommentsRatings(ReturnMovieCommentsRatingsRequest request) {
        var movie = iOmdb.getMovie(OmdbRequest.builder()
                .movieId(request.getMovieId())
                .movieTitle(request.getMovieTitle())
                .movieYear(request.getMovieYear())
                .build());
        var movieId = movie.getImdbID();
        var comments = iReturnMovieCommentsRatingsService.getComments(movieId);
        var ratings = iReturnMovieCommentsRatingsService.getRatings(movieId);
        return buildReturnMovieCommentsRatingsResponse(movie, comments, ratings);
    }

    private ReturnMovieCommentsRatingsResponse buildReturnMovieCommentsRatingsResponse(OmdbResponse movie, List<CommentDto> comments, List<RatingDto> ratings) {
        var movieReturn = buildMovieReturnModel(movie);
        var commentsReturn = buildCommentReturnModelList(comments);
        var ratingsReturn = buildRatingReturnModelList(ratings);
        return ReturnMovieCommentsRatingsResponse.builder()
                .movieData(movieReturn)
                .comments(commentsReturn)
                .ratings(ratingsReturn)
                .build();
    }

    private MovieReturnModel buildMovieReturnModel(OmdbResponse movie) {
        return MovieReturnModel.builder()
                .id(movie.getImdbID())
                .title(movie.getTitle())
                .year(movie.getYear())
                .rated(movie.getRated())
                .released(movie.getReleased())
                .runtime(movie.getRuntime())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .writer(movie.getWriter())
                .actors(movie.getActors())
                .plot(movie.getPlot())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .awards(movie.getAwards())
                .poster(movie.getPoster())
                .ratings(buildMovieRatingReturnModelList(movie.getRatings()))
                .metascore(movie.getMetascore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .type(movie.getType())
                .dvd(movie.getDvd())
                .boxOffice(movie.getBoxOffice())
                .production(movie.getProduction())
                .website(movie.getWebsite())
                .build();
    }

    private List<CommentReturnModel> buildCommentReturnModelList(List<CommentDto> comments) {
        return comments.stream().map(comment -> CommentReturnModel.builder()
                .id(comment.getId())
                .username(comment.getUsername())
                .movieId(comment.getMovieId())
                .text(comment.getText())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .commentReference(comment.getCommentReference())
                .commentReply(comment.getCommentReply())
                .repeated(comment.isRepeated())
                .build()
        ).collect(Collectors.toList());
    }

    private List<RatingReturnModel> buildRatingReturnModelList(List<RatingDto> ratings) {
        return ratings.stream().map(rating -> RatingReturnModel.builder()
                .id(rating.getId())
                .username(rating.getUsername())
                .movieId(rating.getMovieId())
                .rating(rating.getRating())
                .build()
        ).collect(Collectors.toList());
    }


    private List<MovieReturnModel.MovieRatingReturnModel> buildMovieRatingReturnModelList(List<OmdbResponse.RatingResponse> response) {
        return response.stream().map(rating -> MovieReturnModel.MovieRatingReturnModel.builder()
                .source(rating.getSource())
                .value(rating.getValue())
                .build()
        ).collect(Collectors.toList());
    }


}
