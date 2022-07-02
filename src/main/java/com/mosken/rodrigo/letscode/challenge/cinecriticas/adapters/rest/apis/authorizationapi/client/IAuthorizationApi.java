package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.client;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.models.LogInResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.authorizationapi.models.ParseTokenResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.apis.config.CustomFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = "authorization", url = "${authorization.api.url}", configuration = CustomFeignClientConfiguration.class)
public interface IAuthorizationApi {

    @PostMapping("/login")
    LogInResponse logIn(@RequestHeader String username,
                        @RequestHeader String password);

    @GetMapping("/token/parse")
    ParseTokenResponse parseToken(@RequestHeader String token);

}

