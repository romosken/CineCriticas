package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnOmdbMovies;

public interface IOmdb {

    OmdbResponse getMovie(OmdbRequest request);

}
