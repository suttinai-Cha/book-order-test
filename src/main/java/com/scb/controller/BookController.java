package com.scb.controller;

import com.scb.dto.BookResponse;
import com.scb.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @GetMapping("/books")
    public ResponseEntity<?> users() {
        List<BookResponse> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }


}
