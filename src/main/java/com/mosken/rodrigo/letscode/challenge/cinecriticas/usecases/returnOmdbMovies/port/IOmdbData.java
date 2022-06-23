package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies.port;

public interface IOmdbData {
    OmdbDataResponse getOmdbData(String movieName, String movieId, String movieYear);
}
