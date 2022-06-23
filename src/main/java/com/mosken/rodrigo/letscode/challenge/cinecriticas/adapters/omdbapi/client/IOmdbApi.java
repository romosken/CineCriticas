package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.client;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.config.CustomFeignClientConfiguration;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.models.OmdbMovie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "omdb", url = "${omdb.api.url}", configuration = CustomFeignClientConfiguration.class)
public interface IOmdbApi {

    @GetMapping
    OmdbMovie getMovie(@RequestParam("apikey") String apiKey,
                       @RequestParam(name = "t", required = false) String name,
                       @RequestParam(name = "i", required = false) String id,
                       @RequestParam(name = "y", required = false) String year);

}

