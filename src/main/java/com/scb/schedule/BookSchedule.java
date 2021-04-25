package com.scb.schedule;

import com.scb.scb.BookClient;
import com.scb.dto.BookResponse;
import com.scb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSchedule {
    private final BookClient bookClient;
    private final BookService bookService;
    @Scheduled(cron = "${update-book.task:0 30 0 ? * SUN}")
    public void updateBook(){
        List<BookResponse> booksRes = bookClient.getBook();
        bookService.updateBook(booksRes);

     }
}
