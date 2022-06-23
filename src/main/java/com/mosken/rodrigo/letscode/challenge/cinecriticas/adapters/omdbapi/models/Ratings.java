package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ratings {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;

}
