package com.scb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookResponse {

    @JsonProperty
    private Long id;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("is_recommended")
    private Boolean isRecommended;
}
