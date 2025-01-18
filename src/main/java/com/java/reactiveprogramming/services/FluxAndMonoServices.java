package com.java.reactiveprogramming.services;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLOutput;
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
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("APPLE", "MANGO", "BANANA"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    // SWITCH IF EMPTY
    public Flux<String> fruitFluxTransformSwitchIfEmpty(int number) {
        // the following code -- .filter(s -> s.length() > number).log(); is converted into a variable
        // so that we can use the transform operator and then pass the variable filerData
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Apple", "Mango", "Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("JackFruit", "Pineapple"))
                .transform(filterData)
                .log();
        //   .filter(s -> s.length() > number).log();
    }

    // CONCAT AND CONCAT WITH METHOD
    public Flux<String> fruitFluxConcat() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Carrot");
        return Flux.concat(fruits, veggies);
    }

    public Flux<String> fruitFluxConcatWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Carrot");
        return fruits.concatWith(veggies);
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

    public Flux<String> fruitMonoConcatWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        return fruits.concatWith(veggies);
    }

    // MERGE and MERGE WITH methods -- will merge two flux into one but not in sequential manner
    public Flux<String> fruitFluxMerge() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Carrot")
                .delayElements(Duration.ofMillis(75));
        return Flux.merge(fruits, veggies);
    }

    public Flux<String> fruitFluxMergeWith() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Carrot")
                .delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(veggies);
    }

    // Merge Sequential method -- will merge two flux into one but in sequential manner
    public Flux<String> fruitFluxMergeSequential() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Carrot")
                .delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fruits, veggies);
    }

    // Zip and Zip with operator -- will merge the first value of the first flux with the first value of the second flux -- first + second
    public Flux<String> fruitFluxZip() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Carrot");
        return Flux.zip(fruits, veggies,
                (first, second) -> first+second).log();
    }

    public Flux<String> fruitFluxZipWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Carrot");
        return fruits.zipWith(veggies,
                (first, second) -> first+second).log();
    }

    // return the flux as a tuple using the zip method -- this is used when we have more or equal to 2 publishers
    public Flux<String> fruitFluxZipTuple() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Carrot");
        var spices = Flux.just("Pepper", "Chilly");
        return Flux.zip(fruits, veggies, spices)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3()).
                log();
    }

    // zip with Mono
    public Mono<String> fruitMonoZipWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        return fruits.zipWith(veggies,
                (first, second) -> first+second).log();
    }

    // doOn Methods
    public Flux<String> fruitsFluxDoOn(int number) {
        return Flux.fromIterable(List.of("Apple", "Mango", "Banana"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription.toString() = " + subscription.toString());
                })
                .doOnComplete(() -> System.out.println("Completed")).log();
    }

    // Exception handling
    // onError Return
    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("Apple", "Mango")
                .concatWith(Flux.error(
                        new RuntimeException("RunTime Exception Occurred")
                        )).onErrorReturn("Orange");
    }

    // OnErrorContinue
    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux.just("Apple", "Mango", "Orange")
                .<String>handle((s, sink) -> {
                    if(s.equalsIgnoreCase("Mango")) {
                        sink.error(new RuntimeException("Exception Occurred"));
                        return;
                    }
                    sink.next(s.toUpperCase());
                        })
                .onErrorContinue((e,f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }

    // OnErrorMap
    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux.just("Apple", "Mango", "Orange")
                .<String>handle((s, sink) -> {
                    if(s.equalsIgnoreCase("Mango")) {
                        sink.error(new RuntimeException("Exception Occurred"));
                        return;
                    }
                    sink.next(s.toUpperCase());
                })
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From OnError Map");
                });
    }

    // doOnError
    public Flux<String> fruitsFluxDoOnError() {
        return Flux.just("Apple", "Mango", "Orange")
                .<String>handle((s, sink) -> {
                    if(s.equalsIgnoreCase("Mango")) {
                        sink.error(new RuntimeException("Exception Occurred"));
                        return;
                    }
                    sink.next(s.toUpperCase());
                })
                .doOnError(throwable -> {
                    System.out.println("throwable = " + throwable);
                });
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