package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInRequest {
    private String username;
    private String password;
    private String email;
}
