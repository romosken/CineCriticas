package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleBean {

    @Id
    @Enumerated(EnumType.STRING)
    private ERole name;

    @Override
    public String toString(){
        return name.toString();
    }
}