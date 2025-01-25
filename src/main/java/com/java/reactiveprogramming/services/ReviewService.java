package com.java.reactiveprogramming.services;

import com.java.reactiveprogramming.domain.ReviewInfo;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<ReviewInfo> getReviews(long bookId) {
        var reviewList = List.of(new ReviewInfo(1, bookId, 7.2, "example comment one"),
                new ReviewInfo(2, bookId, 7.7, "example comment two")
        );
        return Flux.fromIterable(reviewList);
    }

}