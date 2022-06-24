package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String email;
    private int xp;
    private RoleDto role;
}
