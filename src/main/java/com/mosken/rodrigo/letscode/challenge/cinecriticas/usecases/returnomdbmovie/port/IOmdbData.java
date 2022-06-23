package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.port;

public interface IOmdbData {
    OmdbDataResponse getOmdbData(String movieName, String movieId, String movieYear);
}
