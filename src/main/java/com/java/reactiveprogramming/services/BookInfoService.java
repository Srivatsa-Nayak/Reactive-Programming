package com.java.reactiveprogramming.services;

import com.java.reactiveprogramming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1L, "Book One", "Author One", "216723"),
                new BookInfo(2L, "Book Two", "Author Two", "32623"),
                new BookInfo(3L, "Book Three", "Author Three", "23786")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "Book One", "Author One", "216723");
        return Mono.just(book);
    }

}