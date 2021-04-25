package com.scb.service;

import com.scb.scb.BookClient;
import com.scb.dto.BookResponse;
import com.scb.model.Book;
import com.scb.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookClient bookClient;

    @Override
    public List<BookResponse> getBooks() {
        List<Book> books = getFindAllBook();
        if (books == null || books.size() == 0) {
            List<BookResponse> booksRes = bookClient.getBook();
            updateBook(booksRes);
            books = getFindAllBook();
        }
        List<BookResponse> booksRes = books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());

        return booksRes;
    }

    private final JdbcTemplate jdbcTemplate;

    private List<Book> getFindAllBook() {
        List<Book> response = jdbcTemplate.query("select * from book order by is_recommended  asc ,author_name asc", new BeanPropertyRowMapper<>(Book.class));
        return response;
    }

    @Transactional
    public void updateBook(List<BookResponse> updateBooks) {
        List<Book> books = updateBooks
                .stream()
                .map(book -> modelMapper.map(book, Book.class))
                .collect(Collectors.toList());
        bookRepository.saveAll(books);
    }

}
