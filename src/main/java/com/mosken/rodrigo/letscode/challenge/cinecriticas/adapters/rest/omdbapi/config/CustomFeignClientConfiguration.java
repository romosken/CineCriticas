package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.omdbapi.config;

import feign.Request;
import feign.Retryer;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
//@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class CustomFeignClientConfiguration {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000,1000,3);
    }
    @Bean
    public Request.Options options(){
        return new Request.Options(5L, TimeUnit.SECONDS, 60L, TimeUnit.SECONDS, true);
    }

//    @Bean
//    public ErrorDecoder errorDecoder(){
//        return new ApiErrorDecoder();
//    }

    @Bean
    public WebMvcConfigurer corsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD","TRACE","CONNECT");
            }
        };
    }
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}