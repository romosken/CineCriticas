package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddXpRequest {

    private String username;
    private int xpToAdd;
}
