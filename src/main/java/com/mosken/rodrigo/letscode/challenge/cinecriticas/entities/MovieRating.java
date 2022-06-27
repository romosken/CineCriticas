package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieRating {

    private String source;
    private String value;


}
