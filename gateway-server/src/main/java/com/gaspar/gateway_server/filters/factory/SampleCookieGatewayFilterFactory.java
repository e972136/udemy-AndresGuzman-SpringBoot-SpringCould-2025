package com.gaspar.gateway_server.filters.factory;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


import reactor.core.publisher.Mono;

@Component
public class SampleCookieGatewayFilterFactory extends AbstractGatewayFilterFactory<SampleCookieGatewayFilterFactory.ConfigurationCookie> {

private final Logger log = LoggerFactory.getLogger(SampleCookieGatewayFilterFactory.class);

    public SampleCookieGatewayFilterFactory(){
        super(ConfigurationCookie.class);
    }


    @Override
    public GatewayFilter apply(ConfigurationCookie config) {
        return  (exchange, chain) -> {

            log.info("Ejecutando preGateway filter factory:"+config.message);

            return chain.filter(exchange)
                .then(Mono.fromRunnable(()->{

                    Optional.ofNullable(config.value)
                        .ifPresent(cookie->{
                            exchange.getResponse().addCookie(ResponseCookie.from(config.name,config.value).build());
                        });

                    log.info("Ejecutando postGateway filter factory:"+config.message);
                }));
        };      
    }


    public static record ConfigurationCookie(
        String name,
        String value,
        String message
    ){        
    }


  
}
