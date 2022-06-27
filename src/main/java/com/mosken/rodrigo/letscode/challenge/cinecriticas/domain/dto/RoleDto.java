package com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.dto;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private ERole name;
}
