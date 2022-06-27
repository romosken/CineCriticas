package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInResponse {

    private String username;
    private String password;
    private String email;
    private int xp;
    private ERole role;

}
