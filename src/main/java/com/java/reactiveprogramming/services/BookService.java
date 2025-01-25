package com.java.reactiveprogramming.services;

import com.java.reactiveprogramming.domain.Book;
import com.java.reactiveprogramming.domain.ReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class BookService {

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;


    public Flux<Book> getBookAndReviews() {
        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<ReviewInfo>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                return reviews
                        .map(reviewInfos -> new Book(bookInfo, reviewInfos));
                });
    }

}