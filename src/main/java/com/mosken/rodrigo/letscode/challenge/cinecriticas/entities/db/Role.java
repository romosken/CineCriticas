package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.RoleException;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@Builder
@Data
public class Role {
    private ERole name;

    public Role(ERole name) {
        setName(name);
    }

    public void setName(ERole name) {
        validateAttributeObject(name, "ROLE_NAME");
        this.name = name;
    }

    private void validateAttributeObject(Object attribute, String attributeName) {
        if (Objects.isNull(attribute))
            throw new RoleException(attributeName + " cannot be null or empty!");

    }

    public static class RoleBuilder {
        public Role build() {
            return new Role(
                    name
            );
        }
    }
}