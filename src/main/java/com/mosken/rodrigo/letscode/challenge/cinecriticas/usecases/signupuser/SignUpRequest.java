package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}
