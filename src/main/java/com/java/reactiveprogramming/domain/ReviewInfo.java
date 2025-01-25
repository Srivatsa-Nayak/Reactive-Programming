package com.java.reactiveprogramming.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewInfo {
    private long reviewId;
    private long bookId;
    private double ratings;
    private String comments;

    public ReviewInfo(long reviewId, long bookId, double ratings, String comments) {
        this.reviewId = reviewId;
        this.bookId = bookId;
        this.ratings = ratings;
        this.comments = comments;
    }

}