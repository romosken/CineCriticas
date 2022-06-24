package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.controller;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.IOmdb;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.returnomdbmovie.OmdbResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.ISignIn;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.SignInRequest;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/cinecriticas/v1")
@RequiredArgsConstructor
public class CineController {

    private final IOmdb iOmdb;

    private final ISignIn iSignIn;
    private static final String ID_AND_TITLE_NULL = "At least one argument is required! (id or title)";


    @GetMapping("/search/movie")
    public ResponseEntity<OmdbResponse> getMovie(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "year", required = false) String year
    ) {

        if ((Objects.isNull(title) || title.isBlank()) && (Objects.isNull(id) || id.isBlank()))
            throw new InvalidResourceException(ID_AND_TITLE_NULL);
        var response = iOmdb.getMovie(OmdbRequest.builder().movieTitle(title).movieId(id).movieYear(year).build());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/user/signin")
    public ResponseEntity<SignInResponse> insertUser(
            @RequestBody UserDto user
    ) {
        var response = iSignIn.signIn(SignInRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build());
        return ResponseEntity.status(201).body(response);
    }


}


