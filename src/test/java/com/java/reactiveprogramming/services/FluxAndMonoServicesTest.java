package com.java.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(fruitsFlux).expectNext("Apple", "Mango", "Banana").verifyComplete();
    }

    @Test
    void fruitsMono() {
        var fruitsMono = fluxAndMonoServices.fruitsMono();
        StepVerifier.create(fruitsMono).expectNext("Mango").verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFluxMap = fluxAndMonoServices.fruitsFluxMap();
        StepVerifier.create(fruitsFluxMap).expectNext("APPLE", "MANGO", "BANANA").verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFluxMapFilter = fluxAndMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(fruitsFluxMapFilter).expectNext("BANANA").verifyComplete();
    }

    @Test
    void fruitsFluxFilterAndMap() {
        var fruitsFluxMapAndFilter = fluxAndMonoServices.fruitsFluxFilterAndMap(5);
        StepVerifier.create(fruitsFluxMapAndFilter).expectNext("banana").verifyComplete();
    }

    @Test
    void fruitFluxFlatMap() {
        var fruitsFluxFlatMap = fluxAndMonoServices.fruitFluxFlatMap();
        StepVerifier.create(fruitsFluxFlatMap).expectNextCount(16).verifyComplete();
    }

    @Test
    void fruitFluxFlatMapAsync() {
        var fruitsFluxFlatMapAsync = fluxAndMonoServices.fruitFluxFlatMapAsync();
        StepVerifier.create(fruitsFluxFlatMapAsync).expectNextCount(16).verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitsMonoFlatMap = fluxAndMonoServices.fruitsMonoFlatMap();
        StepVerifier.create(fruitsMonoFlatMap).expectNextCount(1).verifyComplete();
    }

    @Test
    void fruitFluxConcatMapAsync() {
        var fruitsFluxConcatMapAsync = fluxAndMonoServices.fruitFluxConcatMapAsync();
        StepVerifier.create(fruitsFluxConcatMapAsync).expectNextCount(16).verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        var fruitsMonoFlatMapMany = fluxAndMonoServices.fruitsMonoFlatMapMany();
        StepVerifier.create(fruitsMonoFlatMapMany).expectNextCount(5).verifyComplete();
    }

    @Test
    void fruitFluxTransform() {
        var fruitsFluxTransform = fluxAndMonoServices.fruitFluxTransform(5);
        StepVerifier.create(fruitsFluxTransform).expectNext("BANANA").verifyComplete();
    }

    @Test
    void fruitFluxTransformDefaultIfEmpty() {
        var fruitFluxTransformDefaultIfEmpty = fluxAndMonoServices.fruitFluxTransformDefaultIfEmpty(10);
        StepVerifier.create(fruitFluxTransformDefaultIfEmpty).expectNext("Default").verifyComplete();
    }

    @Test
    void fruitFluxTransformSwitchIfEmpty() {
        var fruitFluxTransformSwitchIfEmpty = fluxAndMonoServices.fruitFluxTransformSwitchIfEmpty(7);
        StepVerifier.create(fruitFluxTransformSwitchIfEmpty).expectNext("JackFruit", "Pineapple").verifyComplete();
    }

    @Test
    void fruitFluxConcat() {
        var fruits = fluxAndMonoServices.fruitFluxConcat().log();
        StepVerifier.create(fruits).expectNext("Mango", "Orange", "Tomato", "Carrot").verifyComplete();
    }

    @Test
    void fruitFluxConcatWith() {
        var fruits = fluxAndMonoServices.fruitFluxConcatWith().log();
        StepVerifier.create(fruits).expectNext("Mango", "Orange", "Tomato", "Carrot").verifyComplete();
    }

    @Test
    void fruitMonoConcatWith() {
        var fruits = fluxAndMonoServices.fruitMonoConcatWith().log();
        StepVerifier.create(fruits).expectNext("Mango", "Tomato").verifyComplete();
    }

    @Test
    void fruitFluxMerge() {
        var fruits = fluxAndMonoServices.fruitFluxMerge().log();
        StepVerifier.create(fruits).expectNext("Mango", "Tomato", "Orange","Carrot").verifyComplete();
    }

    @Test
    void fruitFluxMergeWith() {
        var fruits = fluxAndMonoServices.fruitFluxMergeWith().log();
        StepVerifier.create(fruits).expectNext("Mango", "Tomato", "Orange","Carrot").verifyComplete();
    }

    @Test
    void fruitFluxMergeWithSequential() {
        var fruits = fluxAndMonoServices.fruitFluxMergeSequential().log();
        StepVerifier.create(fruits).expectNext("Mango", "Orange", "Tomato", "Carrot").verifyComplete();
    }

    @Test
    void fruitFluxZip() {
        var fruits = fluxAndMonoServices.fruitFluxZip();
        StepVerifier.create(fruits).expectNext("MangoTomato", "OrangeCarrot").verifyComplete();
    }

    @Test
    void fruitFluxZipWith() {
        var fruits = fluxAndMonoServices.fruitFluxZipWith();
        StepVerifier.create(fruits).expectNext("MangoTomato", "OrangeCarrot").verifyComplete();
    }

    @Test
    void fruitFluxZipTuple() {
        var fruits = fluxAndMonoServices.fruitFluxZipTuple();
        StepVerifier.create(fruits).expectNext("MangoTomatoPepper", "OrangeCarrotChilly").verifyComplete();
    }

    @Test
    void fruitMonoZipWith() {
        var fruits = fluxAndMonoServices.fruitMonoZipWith();
        StepVerifier.create(fruits).expectNext("MangoTomato").verifyComplete();
    }

    @Test
    void fruitsFluxDoOn() {
        var fruits = fluxAndMonoServices.fruitsFluxDoOn(5);
        StepVerifier.create(fruits).expectNext("Banana").verifyComplete();
    }

    @Test
    void fruitsFluxOnError() {
        var fruits = fluxAndMonoServices.fruitsFluxOnErrorReturn().log();
        StepVerifier.create(fruits).expectNext("Apple", "Mango", "Orange").verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruits = fluxAndMonoServices.fruitsFluxOnErrorContinue().log();
        StepVerifier.create(fruits).expectNext("APPLE", "ORANGE").verifyComplete();

    }

    @Test
    void fruitsFluxOnErrorMap() {
        var fruits = fluxAndMonoServices.fruitsFluxOnErrorMap().log();
        StepVerifier.create(fruits).expectNext("APPLE").expectError(IllegalStateException.class).verify();
    }

    @Test
    void fruitsFluxDoOnError() {
        var fruits = fluxAndMonoServices.fruitsFluxDoOnError().log();
        StepVerifier.create(fruits).expectNext("APPLE").expectError(RuntimeException.class).verify();
    }
}