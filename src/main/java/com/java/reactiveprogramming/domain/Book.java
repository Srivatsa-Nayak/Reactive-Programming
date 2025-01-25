package com.java.reactiveprogramming.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Book {

    private BookInfo bookInfo;
    private List<ReviewInfo> reviewInfoList;

    public Book(BookInfo bookInfo, List<ReviewInfo> reviewInfoList) {
        this.bookInfo = bookInfo;
        this.reviewInfoList = reviewInfoList;
    }

}