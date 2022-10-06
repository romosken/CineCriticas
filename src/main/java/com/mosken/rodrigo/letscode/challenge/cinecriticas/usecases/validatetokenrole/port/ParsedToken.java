package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.validatetokenrole.port;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParsedToken {

    @JsonProperty("id")
    private String tokenId;
    private String subject;
}
