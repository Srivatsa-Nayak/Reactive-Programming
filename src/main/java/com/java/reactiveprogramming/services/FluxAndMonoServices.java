package com.java.reactiveprogramming.services;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoServices {

    // create a flux from the list that I have
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Banana")).log();
    }

    // create a Mono
    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
    }

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
        // subscribe to the flux and print the data from the list
        fluxAndMonoServices.fruitsFlux().subscribe(s -> {
            System.out.println("Flux -> S - " + s);
        });

        fluxAndMonoServices.fruitsMono().subscribe(s -> {
            System.out.println("Mono -> S - " + s);
        });
    }

}