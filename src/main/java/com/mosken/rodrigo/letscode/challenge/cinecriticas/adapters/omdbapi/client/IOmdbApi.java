package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.client;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.config.CustomFeignClientConfiguration;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.models.OmdbMovie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "omdb", url = "${omdb.api.url}", configuration = CustomFeignClientConfiguration.class)
public interface IOmdbApi {

    @RequestMapping(method = RequestMethod.GET)
    OmdbMovie getMovie(@RequestParam("apikey") String apiKey,
                       @RequestParam(name = "t", required = false) String name,
                       @RequestParam(name = "i", required = false) String id,
                       @RequestParam(name = "y", required = false) String year);

}

