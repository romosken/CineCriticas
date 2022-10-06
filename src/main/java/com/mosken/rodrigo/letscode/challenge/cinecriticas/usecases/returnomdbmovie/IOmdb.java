package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie;

public interface IOmdb {

    OmdbResponse getMovie(OmdbRequest request);

}
