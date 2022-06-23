package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmdbRequest {

    private String movieTitle;
    private String movieId;
    private String movieYear;

}
