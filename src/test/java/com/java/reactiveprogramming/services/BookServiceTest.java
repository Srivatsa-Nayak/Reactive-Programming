package com.java.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private final BookInfoService bookInfoService = new BookInfoService();
    private final ReviewService reviewService = new ReviewService();
    private final BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBookAndReviews() {
        var books = bookService.getBookAndReviews().log();
        StepVerifier.create(books)
                .assertNext(book ->  {
                    assertEquals("Book One", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewInfoList().size());
                })
                .assertNext(book ->  {
                    assertEquals("Book Two", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewInfoList().size());
                })
                .assertNext(book ->  {
                    assertEquals("Book Three", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewInfoList().size());
                })
                .verifyComplete();
    }
}