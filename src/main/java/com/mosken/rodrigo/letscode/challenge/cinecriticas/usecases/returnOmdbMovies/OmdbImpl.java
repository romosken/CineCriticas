package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.Movie;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.Rating;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies.port.IOmdbData;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies.port.OmdbDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OmdbImpl implements IOmdb{

    private final IOmdbData iOmdbData;


    @Override
    public OmdbResponse getMovie(OmdbRequest request) {
        //TODO: Adicionar logs
        var response = iOmdbData.getOmdbData(request.getMovieTitle(), request.getMovieId(), request.getMovieYear());
        var responseEntity = buildMovie(response);
        return buildOmdbResponse(responseEntity);
    }

    private Movie buildMovie(OmdbDataResponse response) {
        return Movie.builder()
                .title(response.getTitle())
                .year(response.getYear())
                .rated(response.getRated())
                .released(response.getReleased())
                .runtime(response.getRuntime())
                .genre(response.getGenre())
                .director(response.getDirector())
                .writer(response.getWriter())
                .actors(response.getActors())
                .plot(response.getPlot())
                .language(response.getLanguage())
                .country(response.getCountry())
                .awards(response.getAwards())
                .poster(response.getPoster())
                .ratings(buildRatingEntityList(response.getRatings()))
                .metascore(response.getMetascore())
                .imdbRating(response.getImdbRating())
                .imdbVotes(response.getImdbVotes())
                .imdbID(response.getImdbID())
                .type(response.getType())
                .dvd(response.getDvd())
                .boxOffice(response.getBoxOffice())
                .production(response.getProduction())
                .website(response.getWebsite())
                .build();

    }
    private List<Rating> buildRatingEntityList(List<OmdbDataResponse.MovieRatings> response) {
        if(Objects.isNull(response))
            return Collections.emptyList();
        return response.stream().map(rating -> Rating
                .builder()
                .source(rating.getSource())
                .value(rating.getValue())
                .build()
        ).collect(Collectors.toList());
    }

    private OmdbResponse buildOmdbResponse(Movie movie) {
        return OmdbResponse.builder()
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
                .ratings(buildRatingResponseList(movie.getRatings()))
                .metascore(movie.getMetascore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .imdbID(movie.getImdbID())
                .type(movie.getType())
                .dvd(movie.getDvd())
                .boxOffice(movie.getBoxOffice())
                .production(movie.getProduction())
                .website(movie.getWebsite())
                .build();

    }
    private List<OmdbResponse.RatingResponse> buildRatingResponseList(List<Rating> response) {
        return response.stream().map(rating -> OmdbResponse.RatingResponse
                .builder()
                .source(rating.getSource())
                .value(rating.getValue())
                .build()

        ).collect(Collectors.toList());
    }



}
