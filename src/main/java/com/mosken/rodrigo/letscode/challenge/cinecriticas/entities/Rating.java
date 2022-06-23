package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.MovieException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.RatingException;
import lombok.*;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rating {

    private String source;
    private String value;


}
