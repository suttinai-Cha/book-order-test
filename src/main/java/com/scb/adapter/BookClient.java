package com.scb.scb;

import com.scb.dto.BookResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookClient {
    private final RestTemplate restTemplate;


    public List<BookResponse> getBook() {
        ResponseEntity<List<BookResponse>> bookResponse =
                restTemplate.exchange("https://scb-test-book-publisher.herokuapp.com/books",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<BookResponse>>() {
                        });
        Map<Long, BookResponse> map = bookResponse.getBody().stream().collect(
                Collectors.toMap(BookResponse::getId, Function.identity()));

        ResponseEntity<List<BookResponse>> bookRecResponse =
                restTemplate.exchange("https://scb-test-book-publisher.herokuapp.com/books/recommendation",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<BookResponse>>() {
                        });
        for (BookResponse recBook :
                bookRecResponse.getBody()) {
           if( map.containsKey(recBook.getId())){
               recBook.setIsRecommended(true);
               map.replace(recBook.getId(),recBook);
           }
        }
        List<BookResponse> result = map.values().stream()
                .collect(Collectors.toList());
        return result;
    }

}
