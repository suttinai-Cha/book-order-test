package com.scb.service;

import com.scb.dto.BookResponse;

import java.util.List;

public interface BookService {


    public List<BookResponse> getBooks();
    public void updateBook(List<BookResponse> updateBooks);
}
