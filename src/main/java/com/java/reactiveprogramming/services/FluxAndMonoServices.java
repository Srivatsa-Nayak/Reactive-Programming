package com.java.reactiveprogramming.services;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {

    // FLUX
    // create a flux from the list that I have
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Banana")).log();
    }

    // MAP METHOD
    // the below piece of code converts one set of flux to another -- ex. list containing lower case string variables into upper case
    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Banana"))
                .map(String::toUpperCase).log();
    }

    // FILTER METHOD
    //  -- filter based on the input given
    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .filter(s -> s.length() > number).log();
    }

    // filter and map example -- filter according to the length and maps to lowercase
    public Flux<String> fruitsFluxFilterAndMap(int number) {
        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .filter(s -> s.length() > number)
                .map(String::toLowerCase)
                .log();
    }

    // FLATMAP METHOD
    // flatMap -- convert each and every element in a list to 0 to n flux characters -- ex: Mango to a, M, g, n, o.....
    // flatMap is not sequential , order of the data will not be preserved
    public Flux<String> fruitFluxFlatMap() {
        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    // flatMap is an asynchronous one
    public Flux<String> fruitFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .flatMap(s -> Flux.just(s.split(""))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                )))
                .log();
    }

    // CONCATMAP METHOD
    // ConcatMap will preserve the order of the elements - data will be omitted in a sequential order , input - abc , output a,b,c
    public Flux<String> fruitFluxConcatMapAsync() {
        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .concatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    // TRANSFORM OPERATOR
    public Flux<String> fruitFluxTransform(int number) {
        // the following code -- .filter(s -> s.length() > number).log(); is converted into a variable
        // so that we can use the transform operator and then pass the variable filerData
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .transform(filterData).log();
             //   .filter(s -> s.length() > number).log();
    }


    // DEFAULT IF EMPTY METHOD
    // If the passed parameter is 10 and our list does not have any string having length more than 10 , then it returns "Default"
    public Flux<String> fruitFluxTransformDefaultIfEmpty(int number) {
        // the following code -- .filter(s -> s.length() > number).log(); is converted into a variable
        // so that we can use the transform operator and then pass the variable filerData
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
        //   .filter(s -> s.length() > number).log();
    }

    // MONO
    // create a Mono
    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
    }

    // MONO FLATMAP
    public Mono<List<String>> fruitsMonoFlatMap() {
        return Mono.just("Mango")
        .flatMap(s -> Mono.just(List.of(s.split("")))).log();
    }

    // FLATMAP MANY METHOD
    // convert from mono to flux using flat map many method
    public Flux<String> fruitsMonoFlatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                        .log();
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