package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.client;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.exception.OmdbApiException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.models.OmdbMovie;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.models.OmdbRatings;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies.port.IOmdbData;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies.port.OmdbDataResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OmdbDataImpl implements IOmdbData {


    @Value("${omdb.api.key}")
    private String apiKey;
    private final IOmdbApi iOmdbApi;

    private final String NULL_CONTENT = "Movie not found!";

    @Override
    public OmdbDataResponse getOmdbData(String movieName, String movieId, String movieYear){
        var response = iOmdbApi.getMovie(apiKey, movieName, movieId, movieYear);
        return buildResponse(response);

    }

    private OmdbDataResponse buildResponse(OmdbMovie response) {
        var validatedResponse = Optional.ofNullable(response).orElseThrow(() -> new OmdbApiException(NULL_CONTENT));
        if (!Boolean.parseBoolean(validatedResponse.getResponse()))
            throw new OmdbApiException(NULL_CONTENT);

        return OmdbDataResponse.builder()
                .title(validatedResponse.getTitle())
                .year(validatedResponse.getYear())
                .rated(validatedResponse.getRated())
                .released(validatedResponse.getReleased())
                .runtime(validatedResponse.getRuntime())
                .genre(validatedResponse.getGenre())
                .director(validatedResponse.getDirector())
                .writer(validatedResponse.getWriter())
                .actors(validatedResponse.getActors())
                .plot(validatedResponse.getPlot())
                .language(validatedResponse.getLanguage())
                .country(validatedResponse.getCountry())
                .awards(validatedResponse.getAwards())
                .poster(validatedResponse.getPoster())
                .ratings(buildRatingList(validatedResponse.getRatings()))
                .metascore(validatedResponse.getMetascore())
                .imdbRating(validatedResponse.getImdbRating())
                .imdbVotes(validatedResponse.getImdbVotes())
                .imdbID(validatedResponse.getImdbID())
                .type(validatedResponse.getType())
                .dvd(validatedResponse.getDvd())
                .boxOffice(validatedResponse.getBoxOffice())
                .production(validatedResponse.getProduction())
                .website(validatedResponse.getWebsite())
                .build();


    }


    private List<OmdbDataResponse.MovieRatings> buildRatingList(List<OmdbRatings> response) {
        if(Objects.isNull(response))
            return Collections.emptyList();
        return response.stream().map(rating -> OmdbDataResponse.MovieRatings
                .builder()
                .source(rating.getSource())
                .value(rating.getValue())
                .build()

        ).collect(Collectors.toList());
    }



}
