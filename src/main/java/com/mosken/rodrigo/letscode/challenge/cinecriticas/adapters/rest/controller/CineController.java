package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.controller;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/cinecriticas/v1")
@RequiredArgsConstructor
public class CineController {

    private final IOmdb iOmdb;

    private static final String ID_AND_TITLE_NULL = "At least one argument is required! (id or title)";


    @GetMapping("/search/movie")
    public ResponseEntity<OmdbResponse> getMovie(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "year", required = false) String year
    ){


        if ((Objects.isNull(title) || title.isBlank()) && (Objects.isNull(id) || id.isBlank()))
            throw new InvalidResourceException(ID_AND_TITLE_NULL);

        var response = iOmdb.getMovie(OmdbRequest.builder().movieTitle(title).movieId(id).movieYear(year).build());

        return ResponseEntity.ok(response);


    }

}
