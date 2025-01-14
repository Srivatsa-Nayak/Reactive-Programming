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
}