package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponse {

    private String token;
}