package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.omdbapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OmdbRatings {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;

}
