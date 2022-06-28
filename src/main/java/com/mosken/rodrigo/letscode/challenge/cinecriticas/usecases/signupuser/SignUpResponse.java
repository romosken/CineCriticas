package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signupuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpResponse {

    private String username;
    private String password;
    private String email;
    private int xp;
    private ERole role;

}
