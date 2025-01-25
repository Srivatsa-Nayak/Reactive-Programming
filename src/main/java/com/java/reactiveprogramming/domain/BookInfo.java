package com.java.reactiveprogramming.domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class BookInfo {

    private long bookId;
    private String title;
    private String author;
    private String ISBN;

    public BookInfo(long bookId, String title, String author, String ISBN) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

}