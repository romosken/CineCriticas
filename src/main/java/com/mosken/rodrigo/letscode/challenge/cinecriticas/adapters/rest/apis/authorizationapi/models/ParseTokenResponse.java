package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParseTokenResponse {

    @JsonProperty("id")
    private String tokenId;
    private String subject;

}
