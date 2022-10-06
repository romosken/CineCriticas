package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_id")
    private String movieId;

    private String username;

    private int rating;

}