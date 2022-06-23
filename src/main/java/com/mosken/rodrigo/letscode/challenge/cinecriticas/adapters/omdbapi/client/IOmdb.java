package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.client;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.config.CustomFeignClientConfiguration;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.omdbapi.models.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "omdb", url = "${omdb.api.url}", configuration = CustomFeignClientConfiguration.class)
public interface IOmdb {

    @RequestMapping(method = RequestMethod.GET)
    Movie getMovie(@RequestParam("apikey") String apiKey,
                   @RequestParam(value = "t", required = false) String name,
                   @RequestParam(value = "i", required = false) String id,
                   @RequestParam(value = "y", required = false) String year);
//
//        @RequestMapping(method = RequestMethod.GET, value = "/airlines/{airlineId}")
//        AirlineResponse readAirLineById(@PathVariable String airlineId);
//
//        @RequestMapping(method = RequestMethod.GET, value = "/passenger")
//        PaginatedPassengerResponse readPassengers(@RequestParam("size") Long size, @RequestParam("page") Long page);
//
//        @RequestMapping(method = RequestMethod.POST, value = "/airlines")
//        AirlineResponse createAirline(@RequestBody AirlineCreateRequest airlineCreateRequest);
//
//        @RequestMapping(method = RequestMethod.DELETE, value = "/passenger/{passengerId}")
//        String deletePassenger(@PathVariable String passengerId);
//
//        @RequestMapping(method = RequestMethod.PATCH, value = "/passenger/{passengerId}")
//        String updatePassenger(@PathVariable String passengerId, @RequestBody PassengerUpdateRequest request);

}

