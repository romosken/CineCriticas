package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private ERole name;

    @Override
    public String toString(){
        return name.toString();
    }
}