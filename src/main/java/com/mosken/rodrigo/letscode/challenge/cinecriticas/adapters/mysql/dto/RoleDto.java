package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.dto;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.ERole;
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
