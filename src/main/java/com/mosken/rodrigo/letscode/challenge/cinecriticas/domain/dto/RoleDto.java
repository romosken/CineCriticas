package com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private ERole name;
}
